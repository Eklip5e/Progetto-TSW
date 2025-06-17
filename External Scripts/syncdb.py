import os
import subprocess
from datetime import datetime
from pathlib import Path

LOG_FILE = "logs/syncdb.log"

DB_USER = "root"
DB_PASS = "studentiTSW"
DB_NAME = "unigame"
DUMP_PATH = Path("..") / "Database" / "dumps" / "dump.sql"
LAST_DUMP_BY_FILE = Path("..") / "Database" / "dumps" / "last_dump_by.txt"

def log(msg, level="INFO"):
    timestamp = datetime.now().strftime("[%Y-%m-%d %H:%M:%S]")
    prefix = {
        "INFO": "",
        "SUCCESS": " - [SUCCESS]",
        "FAIL": " - [FAIL]",
        "WARNING": " - [WARNING]"
    }[level]
    line = f"{timestamp}{prefix} {msg}"

    color = {
        "INFO": "\033[0m",
        "SUCCESS": "\033[92m",
        "FAIL": "\033[91m",
        "WARNING": "\033[93m"
    }[level]

    print(color + line + "\033[0m")

    Path("logs").mkdir(parents=True, exist_ok=True)
    with open(LOG_FILE, "a", encoding="utf-8") as f:
        f.write(line + "\n")

def command_exists(command):
    try:
        subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True, check=True)
        return True
    except subprocess.CalledProcessError:
        return True
    except FileNotFoundError:
        return False

def try_add_mysql_to_path():
    program_dirs = ["C:\\Program Files", "C:\\Programmi"]
    found = False

    for base_dir in program_dirs:
        mysql_root = Path(base_dir) / "MySQL"
        if not mysql_root.exists():
            continue

        for child in mysql_root.iterdir():
            if child.is_dir() and child.name.lower().startswith("mysql server"):
                bin_path = child / "bin"
                if bin_path.exists():
                    os.environ["PATH"] += os.pathsep + str(bin_path)
                    log(f"Aggiunto '{bin_path}' al PATH", level="INFO")
                    found = True
                    break
        if found:
            break

    return found

def verify_and_prepare_mysql_path():
    log("Controllo presenza dei comandi 'mysql' e 'mysqldump'...", level="INFO")

    if command_exists("mysql --version") and command_exists("mysqldump --version"):
        log("Il path globale per mysql e mysqldump è presente", level="SUCCESS")
        return True

    log("Il path non esiste, avvio il processo di fetch", level="FAIL")

    if try_add_mysql_to_path():
        if command_exists("mysql --version") and command_exists("mysqldump --version"):
            log("Il path è integro e funzionante", level="SUCCESS")
            return True
        else:
            log("Nonostante la configurazione del PATH, gli eseguibili mysql e mysqldump non sono presenti, riavviare lo script.", level="FAIL")
            log("Se il problema persiste riconfigurare manualmente il path, riavviare il pc e riavviare lo script con MySQL Workbench avviato.", level="WARNING")
            return False
    else:
        log("Nessuna installazione MySQL trovata in Program Files o Programmi", level="FAIL")
        log("Se il problema persiste riconfigurare manualmente il path, riavviare il pc e riavviare lo script con MySQL Workbench avviato.", level="WARNING")
        return False

def check_mysql_connection():
    log(f"Tento connessione al DB come '{DB_USER}'...", level="INFO")
    try:
        subprocess.run(
            f'mysql -u {DB_USER} -p{DB_PASS} -e "SELECT 1;"',
            shell=True,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            check=True,
        )
        log("Connessione riuscita.", level="SUCCESS")
        return True
    except subprocess.CalledProcessError:
        log("Connessione fallita, controlla username/password e server MySQL.", level="FAIL")
        return False

def read_last_dump_by():
    if not LAST_DUMP_BY_FILE.exists():
        return None
    with open(LAST_DUMP_BY_FILE, "r", encoding="utf-8") as f:
        return f.read().strip()

def write_last_dump_by(hostname):
    with open(LAST_DUMP_BY_FILE, "w", encoding="utf-8") as f:
        f.write(hostname)

def create_dump():
    log("Creo dump del database...", level="INFO")
    try:
        subprocess.run(
            f'mysqldump -u {DB_USER} -p{DB_PASS} {DB_NAME} > "{DUMP_PATH}"',
            shell=True,
            check=True,
        )
        log("Dump creato correttamente.", level="SUCCESS")
    except subprocess.CalledProcessError:
        log("Errore durante la creazione del dump.", level="FAIL")
        exit(1)

def import_dump():
    log(f"Importo il dump di un altro PC...", level="INFO")
    try:
        subprocess.run(
            f'mysql -u {DB_USER} -p{DB_PASS} {DB_NAME} < "{DUMP_PATH}"',
            shell=True,
            check=True,
        )
        log("Importazione completata.", level="SUCCESS")
    except subprocess.CalledProcessError:
        log("Errore durante l'importazione del dump.", level="FAIL")
        exit(1)

def main():
    if not verify_and_prepare_mysql_path():
        exit(1)

    if not check_mysql_connection():
        exit(1)

    current_host = subprocess.check_output("hostname", shell=True).decode().strip()

    last_dump_by = read_last_dump_by()

    if last_dump_by == current_host:
        log("Il dump è già stato creato da questa macchina. Nessuna importazione necessaria.", level="INFO")
        create_dump()
        write_last_dump_by(current_host)
    else:
        import_dump()
        create_dump()
        write_last_dump_by(current_host)

    log("Script completato.", level="SUCCESS")

if __name__ == "__main__":
    main()
