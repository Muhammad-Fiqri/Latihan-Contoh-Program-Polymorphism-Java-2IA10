import java.util.Scanner;

public class Main {
    // Kelas dasar
    static abstract class rekeningBank {
        // Metode yang akan di-override oleh kelas turunan
        String username;
        String pin;
        int saldo;
        int noRek;
        int limit;

        protected rekeningBank(String username, String pin, int noRek) {
            this.username = username;
            this.pin = pin;
            this.saldo = 50000;
            this.noRek = noRek;
            this.limit = 1000000;
        }

        void viewSaldo() {
            System.out.println("Saldo Anda: " + this.saldo);
        }

        void withdraw(int amount) {
            if(logOn() && amount <= limit) {
                this.saldo -= amount;
            }
        }

        void deposit(int amount) {
            if(logOn() && amount <= limit) {
                this.saldo += amount;
            }
        }

        private Boolean logOn() {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Masukkan Username Anda: ");
            String username = scanner.nextLine();

            if(username.equals(this.username)) {
                System.out.println("Username benar");
            } else {
                scanner.close();
                return false;
            }
            
            System.out.print("Masukkan PIN Anda: ");
            String pin = scanner.nextLine();

            if(pin.equals(this.pin)) {
                System.out.println("PIN benar");
                scanner.close();
                return true;
            } else {
                scanner.close();
                return false;
            }
        }
    }

    static class rekeningBankTabunganDeposito extends rekeningBank {

        protected rekeningBankTabunganDeposito(String username, String pin, int noRek) {
            super(username, pin, noRek);
            this.username = username;
            this.pin = pin;
            this.saldo = 50000;
            this.noRek = noRek;
            this.limit = 1000000;
        }

        @Override
        void withdraw(int amount) {
            System.out.println("Tabungan Hanya Bisa Di Tarik Di Teller dan Waktu Pembekuan Telah Habis");
        }
    }

    public static void main(String[] args) {
        
        rekeningBank RekBank = new rekeningBankTabunganDeposito("admin", "1234", 123456789);

        RekBank.deposit(1000000);
        RekBank.viewSaldo();
        RekBank.withdraw(1000);
    }
}
