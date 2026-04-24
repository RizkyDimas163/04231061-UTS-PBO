import java.util.Scanner

// ===== ENTITAS =====
class Peserta(val nama: String)

class Instruktur(val nama: String)

class KelasKursus(
    var namaKelas: String,
    private var kapasitasMax: Int,
    val instruktur: Instruktur
) {
    // Enkapsulasi
    private val daftarPeserta = mutableListOf<Peserta>()

    // Method daftar dengan validasi
    fun daftar(peserta: Peserta) {
        if (daftarPeserta.size >= kapasitasMax) {
            println("❌ PENDAFTARAN DITOLAK: Kelas '$namaKelas' sudah penuh!")
        } else {
            daftarPeserta.add(peserta)
            println("✅ ${peserta.nama} berhasil masuk ke kelas '$namaKelas'")
        }
    }

    // Tampilkan siswa aktif
    fun tampilkanPeserta() {
        println("\n=== DAFTAR SISWA AKTIF ===")
        println("Jumlah: ${daftarPeserta.size} orang")
        if (daftarPeserta.isEmpty()) {
            println("Belum ada peserta.")
        } else {
            daftarPeserta.forEach {
                println("- ${it.nama}")
            }
        }
    }
}

// ===== MAIN =====
fun main() {
    val input = Scanner(System.`in`)

    println("=== SETUP KELAS ===")

    // Input nama kelas
    print("Masukkan nama kelas: ")
    val namaKelas = input.nextLine()

    // Input kapasitas
    print("Masukkan kapasitas maksimal peserta: ")
    val kapasitas = input.nextLine().toInt()

    // Input instruktur
    print("Masukkan nama instruktur: ")
    val namaInstruktur = input.nextLine()

    val instruktur = Instruktur(namaInstruktur)
    val kelas = KelasKursus(namaKelas, kapasitas, instruktur)

    println("\n=== PENDAFTARAN PESERTA ===")

    while (true) {
        print("Masukkan nama peserta (ketik 'exit' untuk selesai): ")
        val nama = input.nextLine()

        if (nama.lowercase() == "exit") break

        val peserta = Peserta(nama)
        kelas.daftar(peserta)
    }

    kelas.tampilkanPeserta()
}