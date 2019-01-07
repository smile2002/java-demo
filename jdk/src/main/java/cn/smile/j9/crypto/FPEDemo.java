package cn.smile.j9.crypto;


import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.idealista.fpe.config.Alphabet;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.GenericDomain;
import com.idealista.fpe.config.GenericTransformations;
import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;

import java.nio.channels.InterruptedByTimeoutException;

/**
 * Created by Smile on 2019/1/7.
 */
public class FPEDemo {

    public static void main( String[] args )
    {
        Alphabet alphabet = new Alphabet() {
            @Override
            public char[] availableCharacters() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            }

            @Override
            public Integer radix() {
                return 10;
            }
        };
        GenericTransformations trans = new GenericTransformations(alphabet.availableCharacters());
        GenericDomain myDomain = new GenericDomain(alphabet, trans, trans);

        try {
            FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                    .ff1Implementation()
                    .withDomain(myDomain)
                    .withDefaultPseudoRandomFunction("unionpay20180505".getBytes())
                    .withDefaultLengthRange()
                    .build();

            int i;
            for (i=0; i < 200; i++) {
                String number = String.format("%012d", i);
                String cipherText = formatPreservingEncryption.encrypt(number, "123".getBytes());
                System.out.println(number + " -> " + cipherText);

                String plainText = formatPreservingEncryption.decrypt(cipherText, "123".getBytes());
                if (!number.equals(plainText)) {
                    throw new Exception("plain not match");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
