import java.math.BigInteger;

public class TextbookRSA {

    /*
        1.  Create a number N (N = pq)
            * phi(N) = phi(pq) = phi(p)phi(q) = (p-1)*(q-1) = pq - p - q + 1 = N - p - q + 1
        2.  Choose integers e and d (e*d = 1 (mod phi(N)))
            * e = 3
        3.  pk = (N, e), sk = (N, d)
        4.  Encrypt: c <- m^e (in Zn)
        5.  Decrypt: c^d -> m
    */

    private BigInteger N, p, q, phiN;
    private BigInteger e, d;
    private BigInteger m, c;

    // 1. Generate key
    public TextbookRSA() {
        while(true) {
            createN();
            chooseEandD();
            if(d == null) continue;
            else break;
        }
    }

    // 2. Encrypt & Decrypt
    public TextbookRSA(BigInteger N, BigInteger eOrd, BigInteger cOrm, String mode) {
        this.N = N;

        if(mode.equals("Encrypt")) {
            this.e = eOrd;
            this.m = cOrm;
            this.c = m.modPow(e, N);
        } else if(mode.equals("Decrypt")) {
            this.d = eOrd;
            this.c = cOrm;
            this.m = c.modPow(d, N);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private void createN() {
        p = BigInteger.ZERO;
        q = BigInteger.ZERO;

        while(p.toString().equals(q.toString())) {
            p = new MillerRabinPrimality(1024).getPrime();
            q = new MillerRabinPrimality(1024).getPrime();
        }

        N = p.multiply(q);
        phiN = N.subtract(p).subtract(q).add(BigInteger.ONE);
    }

    private void chooseEandD() {
        e = new BigInteger("3");
        d = new Inverse(phiN, e).getxInv();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getPk(int notation) { return "(" + N.toString(notation) + ", " + e.toString(notation) + ")"; }
    public String getSk(int notation) { return "(" + N.toString(notation) + ", " + d.toString(notation) + ")"; }
    public String getPlainText(int notation) { return m.toString(notation); }
    public String getCipherText(int notation) { return c.toString(notation); }

    public void testPrint() {
        System.out.println();
        System.out.println("p: " + p.toString());
        System.out.println("q: " + q.toString());

        System.out.println("N: " + p.toString());
        System.out.println("phi(N): " + p.toString());

        System.out.println("e: " + p.toString());
        System.out.println("d: " + p.toString());
        System.out.println();
    }
}
