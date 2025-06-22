package com.unigame.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Utente {
    private int idUtente;
    private Boolean admin;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private Date dataDiNascita;

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // Verifica se la password è già hashata (SHA-1 ha 40 caratteri esadecimali)
        if (password == null || password.length() != 40) {
            try {
                // Hashing della password solo se non è già hashata
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.reset();
                digest.update(password.getBytes(StandardCharsets.UTF_8));
                this.password = String.format("%040x", new BigInteger(1, digest.digest()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Errore durante l'hashing della password", e);
            }
        } else {
            // Se la password è già hashata, assegnala direttamente
            this.password = password;
        }
    }

    // Metodo per verificare la password
    public boolean checkPassword(String rawPassword) {
        try {
            // Hash della password inserita dall'utente
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(rawPassword.getBytes(StandardCharsets.UTF_8));
            String hashedInput = String.format("%040x", new BigInteger(1, digest.digest()));
            return this.password.equals(hashedInput);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore durante l'hashing della password", e);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
}