import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTablo {
    public static final int TABLO_BOYUTU = 10;
    public Kargo[] hash_tablo;
    public HashTablo() {
        hash_tablo = new Kargo[TABLO_BOYUTU];
    }

    public int hashindex1(int takipno) {
        return takipno % TABLO_BOYUTU;
    }
    public int hashindex2(int takipno) {
        return 7 - (takipno % 7);
    }
    public int doubleHashing(int takipNo, int deneme) {
        return (hashindex1(takipNo) + deneme * hashindex2(takipNo)) % TABLO_BOYUTU;
    }

    public void KargoOlustur() throws FileNotFoundException {
        File dosya = new File("C:\\Users\\burak\\IdeaProjects\\f231220002_odev_3\\src\\veriler.txt");
        Scanner sc = new Scanner(dosya);
        sc.useDelimiter("\\s");
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                int takipNo = sc.nextInt();
                String gonderici = sc.next();
                String alici = sc.next();
                String kargoDurum = sc.next();
                Kargo yeniKargo = new Kargo(takipNo, gonderici, alici, kargoDurum);
                KargoEkle(yeniKargo);
            } else {
                sc.next();
            }
        }
        sc.close();
    }
    public void KargoEkle(Kargo yeniKargo) {
        int takipNo = yeniKargo.takipNo;
        int index = hashindex1(takipNo);


        if (hash_tablo[index] == null) {
            hash_tablo[index] = yeniKargo;
            System.out.println(takipNo + ": " + index + ".indexe eklendi.");
        } else {
            int deneme = 1;
            while (hash_tablo[index] != null && deneme < TABLO_BOYUTU) {
                index = doubleHashing(takipNo, deneme);
                deneme++;
            }

            if (deneme < TABLO_BOYUTU) {
                hash_tablo[index] = yeniKargo;
                System.out.println(takipNo + ": " + index + ".indexe eklendi.");
            } else {
                System.out.println("Hash tablosu dolu, kargo eklenemedi.");
            }
        }
    }
    public Kargo KargoArama(int takipNo) {
        int index = hashindex1(takipNo);
        int deneme = 0;
        if (hash_tablo[index] != null && hash_tablo[index].takipNo == takipNo) {
            System.out.println("Kargo " + (deneme + 1) + ". adımda bulundu.");
            return hash_tablo[index];
        }
        while (hash_tablo[index] != null) {
            index = doubleHashing(takipNo, deneme);
            deneme++;

            if (hash_tablo[index] != null && hash_tablo[index].takipNo == takipNo) {
                System.out.println("Kargo " + (deneme + 1) + ". adımda bulundu.");
                return hash_tablo[index];
            }

            if (deneme > TABLO_BOYUTU) {
                break;
            }
        }

        return null;
    }
    public void KargoTeslim(int takipNo) {
        Kargo kargo = KargoArama(takipNo);
        if (kargo != null) {
            int index = hashindex1(takipNo);
            int deneme = 1;

            while (hash_tablo[index] != null) {
                if (hash_tablo[index].takipNo == takipNo) {
                    hash_tablo[index] = null;
                    System.out.println("Kargo teslim edildi: Takip No - " + takipNo);
                    return;
                }

                index = doubleHashing(takipNo, deneme);
                deneme++;

                if (deneme > TABLO_BOYUTU) {
                    break;
                }
            }
        } else {
            System.out.println("Böyle bir kargo zaten yok.");
        }
    }
}

