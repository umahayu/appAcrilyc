package com.ummhay.appacr.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {

    //Acr
    @Query ("SELECT * FROM acr")
    fun getAllAcr() :List<Akrilik>
    @Insert
    fun InsrtAcr(akrilik: Akrilik)
    @Update
    fun UpdteAcr(akrilik: Akrilik)
    @Delete
    fun DltAcr(akrilik: Akrilik)
    @Query("SELECT*FROM acr WHERE id_acr=:idacr")
    fun getAcrByID (idacr:Int) : List<Akrilik>
    @Query("SELECT nama_acr FROM acr")
    fun getNmaAcr() : Array<String>
//    @Query("SELECT persediaan FROM 'akrilik'")
//    fun getJmlhAcr() : LiveData<Array<Int>>
//    @Query("UPDATE 'akrilik' SET persediaan = :newPersediaan WHERE id = :id")
//    fun UpdtPrs(newPersediaan : Int, id: Int)
    @Query("SELECT id_acr FROM acr WHERE nama_acr = :nmaAcrlk")
    fun getID(nmaAcrlk: String) : Int
    @Query("SELECT * FROM acr WHERE nama_acr LIKE :key")
    fun cariNmaAcr(key: String) : List<Akrilik>

    //Psn
    @Query ("SELECT * FROM psn")
    fun getAllPesanan() :List<Pesanan>
    @Insert
    fun InsertPesanan(pesanan: Pesanan)
    @Update
    fun UpdatePesanan(pesanan: Pesanan)
    @Delete
    fun DeletePesanan(pesanan: Pesanan)
    @Query("SELECT*FROM psn ORDER BY id_psn DESC")
    fun getAllP():List<Pesanan>
    @Query("SELECT*FROM psn WHERE id_psn=:idpsn")
    fun getIDPesanan (idpsn:Int) : List<Pesanan>
    @Query("SELECT * FROM psn WHERE id_psn = :id_kendaraan")
    fun cekKendaraanYgDigunakan(id_kendaraan: Int) : Boolean
//    @Query("SELECT COUNT(*) FROM pesanan WHERE status = 'Sewa'")
//    fun getJumlahPesananSewa() : LiveData<Int>
//    @Query("SELECT COUNT(*) FROM pesanan WHERE status = 'Selesai'")
//    fun getJumlahPesananSelesai() : LiveData<Int>
    @Query("SELECT * FROM psn WHERE nama_psn LIKE :key OR alamat_psn LIKE :key")
    fun cariPesanan(key: String) : List<Pesanan>
//    @Query("SELECT * FROM pesanan WHERE status = :status")
//    fun getByStatus(status: String) : List<Pesanan>

}