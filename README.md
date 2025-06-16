# Proyek Akhir PPPL - Aplikasi Manajemen Karyawan

Repositori ini berisi kode sumber untuk Proyek Akhir mata kuliah Pengembangan dan Pengujian Perangkat Lunak (PPPL). Aplikasi yang dikembangkan adalah sebuah sistem informasi sederhana berbasis web untuk manajemen data karyawan.

Sistem ini dirancang untuk digunakan oleh seorang **Owner** (pemilik/admin) untuk mengelola data karyawan di dalam perusahaannya.

---

## 🚀 Fitur Utama

Aplikasi ini memiliki beberapa fitur inti, yaitu:
* **Autentikasi Owner**: Sistem login yang aman untuk memastikan hanya admin yang dapat mengakses dashboard.
* **Dashboard Utama**: Halaman sambutan setelah berhasil login.
* **Manajemen Karyawan (CRUD)**:
    * **Create**: Menambahkan data karyawan baru.
    * **Read**: Melihat daftar semua karyawan yang tersimpan.
    * **Update**: Mengedit informasi karyawan yang sudah ada.
    * **Delete**: Menghapus data karyawan dari sistem.

---

## 🧪 Pengujian Perangkat Lunak

Untuk memastikan kualitas dan keandalan aplikasi, dilakukan **Pengujian End-to-End (E2E)**. Tujuan dari pengujian E2E adalah untuk memvalidasi alur kerja aplikasi secara keseluruhan dari perspektif pengguna, mulai dari login hingga melakukan semua fungsi utama.

### Alur Skenario Pengujian End-to-End

Pengujian dilakukan dengan mengikuti urutan skenario yang saling berhubungan untuk mensimulasikan penggunaan aplikasi di dunia nyata. Berikut adalah alur pengujian yang dijalankan:

1.  **Login Gagal (Email Tidak Terdaftar)**
    * **Aksi**: Mencoba login menggunakan alamat email yang belum pernah didaftarkan di sistem.
    * **Hasil yang Diharapkan**: Sistem menolak login dan menampilkan pesan kesalahan yang relevan.

2.  **Login Gagal (Password Salah)**
    * **Aksi**: Mencoba login menggunakan email yang terdaftar tetapi dengan password yang salah.
    * **Hasil yang Diharapkan**: Sistem menolak login dan menampilkan pesan kesalahan mengenai kredensial yang tidak valid.

3.  **Login Berhasil**
    * **Aksi**: Login menggunakan email dan password yang valid.
    * **Hasil yang Diharapkan**: Sistem berhasil melakukan autentikasi dan mengarahkan pengguna ke halaman beranda (dashboard).

4.  **Akses Halaman Beranda**
    * **Aksi**: Memastikan halaman beranda termuat dengan benar setelah login.
    * **Hasil yang Diharapkan**: Semua komponen di dashboard (misalnya, menu navigasi, informasi sambutan) tampil dengan benar.

5.  **Navigasi ke Manajemen Karyawan**
    * **Aksi**: Dari dashboard, klik menu untuk masuk ke halaman "Manajemen Karyawan".
    * **Hasil yang Diharapkan**: Aplikasi berhasil pindah ke halaman yang menampilkan daftar karyawan.

6.  **Tambah Karyawan Gagal (Data Wajib Kosong)**
    * **Aksi**: Mencoba menyimpan data karyawan baru tanpa mengisi kolom yang wajib diisi (misalnya, nama atau email).
    * **Hasil yang Diharapkan**: Sistem menolak penyimpanan dan menampilkan validasi error pada kolom yang kosong.

7.  **Tambah Karyawan Gagal (Email Duplikat)**
    * **Aksi**: Mencoba menambahkan karyawan baru dengan alamat email yang sudah digunakan oleh karyawan lain.
    * **Hasil yang Diharapkan**: Sistem menolak penyimpanan dan memberikan pesan kesalahan bahwa email sudah terdaftar.

8.  **Tambah Karyawan Berhasil**
    * **Aksi**: Mengisi semua data yang diperlukan dengan informasi valid dan menyimpannya.
    * **Hasil yang Diharapkan**: Karyawan baru berhasil ditambahkan ke dalam sistem dan muncul di daftar karyawan.

9.  **Edit Karyawan Gagal (Email Duplikat)**
    * **Aksi**: Mengedit data seorang karyawan dan mengubah emailnya menjadi email yang sudah dipakai oleh karyawan lain.
    * **Hasil yang Diharapkan**: Sistem menolak pembaruan dan menampilkan pesan error duplikasi email.

10. **Edit Karyawan Berhasil**
    * **Aksi**: Mengedit informasi seorang karyawan dengan data yang valid.
    * **Hasil yang Diharapkan**: Data karyawan berhasil diperbarui di dalam sistem.

11. **Hapus Karyawan**
    * **Aksi**: Memilih salah satu data karyawan dan menghapusnya.
    * **Hasil yang Diharapkan**: Data karyawan tersebut terhapus dari daftar dan database.
