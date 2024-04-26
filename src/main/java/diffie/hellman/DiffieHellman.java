/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package diffie.hellman;
import java.math.BigInteger;
import static java.math.BigInteger.TWO;
import java.security.SecureRandom;

/**
 *
 * @author felip
 */
public class DiffieHellman {

    private static final BigInteger ONE = BigInteger.ONE;

    // Função para verificar se um número é primo
    private static boolean isPrime(BigInteger n) {
        return n.isProbablePrime(50);
    }

    // Função para encontrar uma raiz primitiva módulo p
    private static BigInteger findPrimitiveRoot(BigInteger p) {
        for (BigInteger g = TWO; g.compareTo(p) < 0; g = g.add(ONE)) {
            if (g.modPow(p.subtract(ONE), p).equals(ONE)) {
                return g;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Valores comuns escolhidos publicamente
        BigInteger p = new BigInteger("23"); // Um número primo
        BigInteger g = findPrimitiveRoot(p); // Raiz primitiva módulo p

        // Alice escolhe um segredo privado 'a'
        SecureRandom random = new SecureRandom();
        BigInteger a = new BigInteger(10, random);

        // Bob escolhe um segredo privado 'b'
        BigInteger b = new BigInteger(10, random);

        // Alice calcula A = g^a mod p
        BigInteger A = g.modPow(a, p);

        // Bob calcula B = g^b mod p
        BigInteger B = g.modPow(b, p);

        // Alice e Bob trocam os valores A e B

        // Alice calcula o segredo compartilhado: s = B^a mod p
        BigInteger sAlice = B.modPow(a, p);

        // Bob calcula o segredo compartilhado: s = A^b mod p
        BigInteger sBob = A.modPow(b, p);

        // Verifica se os segredos compartilhados são iguais
        if (sAlice.equals(sBob)) {
            System.out.println("Segredo compartilhado (s): " + sAlice);
        } else {
            System.out.println("Erro ao compartilhar o segredo.");
        }
    }
}
