package cl.makinolas.atk.utils;

import cl.makinolas.atk.GameConstants;

public class PhraseCryptor implements Cryptor{

    public int[] key;
    public int n;
    private final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:\"{}()[],.-_#@$%&+*/\\;?!=|<> ";

    public PhraseCryptor(String k) {
        n = k.length();
        key = new int[n];
        for (int i = 0; i < n; i++) {
            key[i] = alphabet.indexOf(k.charAt(i));
        }
    }

    @Override
    public String encrypt(String msg) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            builder.append(alphabet.charAt((alphabet.indexOf(msg.charAt(i))+key[i%n])%alphabet.length()));
        }
        return builder.toString();
    }

    @Override
    public String decrypt(String msg) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            builder.append(alphabet.charAt((alphabet.indexOf(msg.charAt(i))-key[i%n]+alphabet.length())%alphabet.length()));
        }
        if(GameConstants.DEBUG){
          System.out.println(builder.toString());
        }
        return builder.toString();
    }
}
