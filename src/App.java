import java.math.BigInteger;

public class App {

    public static BigInteger N = new BigInteger("19630952806629223318611595859267099214452513816456296755595203477285582410402602044048188534613188004734097260481464899226196288252996485597872761596480587484925100750406373959006643439344524538421887600745535074909098780146816231333123958662293035208288329143907057837510786244495228393833698127726639580286487825505146886519747263924607918280731302162524167864751239733986360751987272525409676724508181276591858950743773899182084103779728290795490308500089613231966143354990189621634170926753060450630291319927572699643958769853870176232897111195348793865696817533030239820387656772495016033259138663296882553812127");
    public static BigInteger e = new BigInteger("3"); // PK
    public static BigInteger d = new BigInteger("13087301871086148879074397239511399476301675877637531170396802318190388273601734696032125689742125336489398173654309932817464192168664323731915174397653724989950067166937582639337762292896349692281258400497023383272732520097877487555415972441528690138858886095938038558340524162996818929222465418484426386857470841780451747113757977521578393318336566745115189676151230099057164954894226098089782076559683497371813149089543568262146822749053813452485480527948436137751157367136908674953603829435201510943740741310924790962439425366644697767254690815534354498792117120980373757966836633888036412915829821449102940208491"); // SK
    public static BigInteger m = new BigInteger("980522"); // Plain Text
    public static BigInteger c = new BigInteger("942696787645196648"); // Cipher Text

    public static void testGenerateKey() {
        TextbookRSA trsa = new TextbookRSA();
        trsa.testPrint();
    }

    public static void testEncrypt() {
        TextbookRSA trsa = new TextbookRSA(N, e, m, "Encrypt");
        System.out.println(trsa.getCipherText());
    }

    public static void testDecrypt() {
        TextbookRSA trsa = new TextbookRSA(N, d, c, "Decrypt");
        System.out.println(trsa.getPlainText());
    }

    public static boolean checkDigits(String s) {
        for(int i=0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i)) == false) return false;
        }
        return true;
    }

    public static void help(String msg) {
        System.out.println("\n" + msg + "\n");
        System.out.println("    -g : Generate key => pk: ({N}, {e})");
        System.out.println("                         sk: ({N}, {d})");
        System.out.println("    -e <N> <e> <plain text>  : Encrypt plain text  => {cipher text}");
        System.out.println("    -d <N> <d> <cipher text> : Decrypt cipher text => {plain text}");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        if(args.length < 1) { help("Please enter the [FLAG]\nThe following flag provides the values in { .. }"); return; }

        if(args[0].equals("-g")) {
            TextbookRSA trsa = new TextbookRSA();
            System.out.println("pk: " + trsa.getPk());
            System.out.println("sk: " + trsa.getSk());
        } else if(args[0].equals("-e")) {
            if(args.length < 4) { help("[-e]: Please enter the <N>, <e> and <plain text>"); return; }
            if(checkDigits(args[1]) && checkDigits(args[2]) && checkDigits(args[3])) {
                TextbookRSA trsa = new TextbookRSA(new BigInteger(args[1]), new BigInteger(args[2]), new BigInteger(args[3]), "Encrypt");
                System.out.println(trsa.getCipherText());
            } else { help("[-e]: <N>, <e> and <plain text> must be number"); return; }
        } else if(args[0].equals("-d")) {
            if(args.length < 4) { help("[-d]: Please enter the <N>, <d> and <cipher text>"); return; }
            if(checkDigits(args[1]) && checkDigits(args[2]) && checkDigits(args[3])) {
                TextbookRSA trsa = new TextbookRSA(new BigInteger(args[1]), new BigInteger(args[2]), new BigInteger(args[3]), "Decrypt");
                System.out.println(trsa.getPlainText());
            } else { help("[-d]: <N>, <d> and <cipher text> must be number"); return; }
        }
        else if(args[0].equals("-help")) { help("Usage: java -cp .:../lib/bignum-projects.jar App [FALG]\nThe following flag provides the values in { .. }"); return; }
        else { help("This [FLAG] does not exist\nThe following flag provides the values in { .. }"); return; }
    }
}

// 숫자를 hex로 바꿔서 제공

// 문자 Encryption: cryptools 이용해서 문자를 숫자로 바꿈(string to hex -> hex to decimal) -> 그 숫자를 encrypt
// 문자 Decryption: 그 숫자를 decrypt -> cryptools 이용해서 숫자를 문자로 바꿈(decimal to hex -> hex to String)
// ** 문자의 길이는 32를 넘지 말아야 함 **