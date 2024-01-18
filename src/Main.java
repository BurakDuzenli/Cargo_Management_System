import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sayi = new Scanner(System.in);
        HashTablo yeni_tablo = new HashTablo();
        while (true) {
            System.out.println("0 - textten veri girişi");
            System.out.println("1 - tüm kargoları listele");
            System.out.println("2 - Elden kargo girişi");
            System.out.println("3 - Kargo takip");
            System.out.println("4 - Kargo teslim et");
            System.out.println("5 - Çıkış yap");
            System.out.print("LÜTFEN YAPMAK İSTEDİĞİNİZ İŞLEMİ SEÇİNİZ:");
            int sayi1 = sayi.nextInt();
            switch (sayi1) {
                case 0:
                    yeni_tablo.KargoOlustur();
                    break;
                case 1:
                    int deneme = 0;
                    for (int i = 0; i < HashTablo.TABLO_BOYUTU; i++) {
                        Kargo hash = yeni_tablo.hash_tablo[i];
                        if (hash != null) {
                            System.out.println(i + ". index " + hash.takipNo + " " + hash.gonderici + " " + hash.alici + " " + hash.kargoDurum);
                            deneme++;
                        } else {
                            System.out.println(i + ". index boştur");
                        }
                    }
                    System.out.println( deneme + " adımda tüm kayıtlar bulunmuştur");
                    break;
                case 2:
                    System.out.print("Takip Numarası:");
                    Scanner giris = new Scanner(System.in);
                    int takipNo = giris.nextInt();
                    System.out.print("Gonderici giriniz:");
                    String gonderici = giris.next();
                    System.out.print("Alıcı giriniz:");
                    String alici = giris.next();
                    System.out.print("Teslimat durum bilgisi giriniz:");
                    String kargoDurum = giris.next();
                    Kargo yeniKargo = new Kargo(takipNo, gonderici, alici, kargoDurum);
                    yeni_tablo.KargoEkle(yeniKargo);
                    break;
                case 3:
                    System.out.print("Takip Numarası giriniz:");
                    Scanner giris1 = new Scanner(System.in);
                    int takipno = giris1.nextInt();
                    Kargo kargo = yeni_tablo.KargoArama(takipno);
                    if(kargo == null) {
                        System.out.print("Aranan kayıt bulunamadı.");
                    } else {
                        System.out.print("Aranan kayıt:" + kargo.takipNo + " " + kargo.gonderici + " " + kargo.alici + " " + kargo.kargoDurum);
                    }
                    break;
                case 4:
                    System.out.print("Takip Numarası giriniz:");
                    Scanner giris2 = new Scanner(System.in);
                    int takipno1 = giris2.nextInt();
                    yeni_tablo.KargoTeslim(takipno1);
                    break;
                case 5:
                    return;
            }
        }
    }
    }
