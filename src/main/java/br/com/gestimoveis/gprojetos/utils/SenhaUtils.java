package br.com.gestimoveis.gprojetos.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {

    public static String encode(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    public static boolean matches(String senha, String hash){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(senha, hash);
    }
    
}
