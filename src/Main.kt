import java.util.Scanner

// ===== ENTITAS =====
class Peserta(val nama: String)

class Instruktur(val nama: String)

class KelasKursus(
    val namaKelas: String,
    private val kapasitasMax: Int,
    val instruktur: Instruktur
) {
    // Enkapsulasi
    private val daftarPeserta = mutableListOf<Peserta>()

    fun daftar(peserta: Peserta) {
        if (daftarPeserta.size >= kapasitasMax) {
            println("❌ PENDAFTARAN DITOLAK: Kelas '$namaKelas' sudah penuh!")
        } else {
            daftarPeserta.add(peserta)
            println("✅ PENDAFTARAN BERHASIL: ${peserta.nama} masuk ke kelas '$namaKelas'")
        }
    }

    fun tampilkanPeserta() {
        println("\n=== DAFTAR SISWA AKTIF ===")
        if (daftarPeserta.isEmpty()) {
            println("Belum ada peserta.")
        } else {
            for (p in daftarPeserta) {
                println("- ${p.nama}")
            }
        }
    }
}

// ===== MAIN PROGRAM =====
fun main() {
    val input = Scanner(System.`in`)

    val instruktur = Instruktur("Pak Budi")
    val kelas = KelasKursus("Kotlin Dasar", 2, instruktur)

    println("=== SISTEM PENDAFTARAN E-COURSE ===")
    println("Instruktur: ${instruktur.nama}")
    println("Kelas: ${kelas.namaKelas}")
    println("Kapasitas Maksimal: 2 orang\n")

    while (true) {
        print("Masukkan nama peserta (ketik 'exit' untuk berhenti): ")
        val nama = input.nextLine()

        if (nama.lowercase() == "exit") {
            break
        }

        val peserta = Peserta(nama)
        kelas.daftar(peserta)
    }

    kelas.tampilkanPeserta()
}