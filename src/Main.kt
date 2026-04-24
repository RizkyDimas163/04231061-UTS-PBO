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

        // Tampilkan status setelah setiap pendaftaran
        tampilkanStatusKelas()
    }

    // Menampilkan status kelas (FITUR BARU)
    fun tampilkanStatusKelas() {
        val jumlahAktif = daftarPeserta.size
        val sisa = kapasitasMax - jumlahAktif

        println("📊 Status Kelas: $jumlahAktif / $kapasitasMax peserta")

        if (sisa > 0) {
            println("🟢 Slot tersisa: $sisa")
        } else {
            println("🔴 Kelas sudah penuh!")
        }
        println("----------------------------------")
    }

    // Tampilkan siswa aktif
    fun tampilkanPeserta() {
        println("\n=== DAFTAR SISWA AKTIF ===")
        println("Jumlah: ${daftarPeserta.size} / $kapasitasMax")

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

    print("Masukkan nama kelas: ")
    val namaKelas = input.nextLine()

    print("Masukkan kapasitas maksimal peserta: ")
    val kapasitas = input.nextLine().toInt()

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