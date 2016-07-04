package cl.makinolas.atk.utils;

public class PhraseCryptor implements Cryptor{

    public char[] key;
    public int n;

    public PhraseCryptor(String k) {
        n = k.length();
        key = new char[n];
        for (int i = 0; i < n; i++) {
            key[i] = (char) (k.charAt(i) - '0');
        }
    }

    @Override
    public String encrypt(String msg) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            builder.append((char)(msg.charAt(i)+key[i%n]));
        }
        return builder.toString();
    }

    @Override
    public String decrypt(String msg) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            builder.append((char)(msg.charAt(i)-key[i%n]));
        }
        return builder.toString();
    }
}
