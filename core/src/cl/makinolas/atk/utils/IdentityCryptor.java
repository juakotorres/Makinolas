package cl.makinolas.atk.utils;

public class IdentityCryptor implements Cryptor{
    @Override
    public String encrypt(String msg) {
        return msg;
    }
    @Override
    public String decrypt(String msg) {
        return msg;
    }
}
