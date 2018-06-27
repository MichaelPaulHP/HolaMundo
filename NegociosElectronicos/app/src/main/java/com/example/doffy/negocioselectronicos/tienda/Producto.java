package com.example.doffy.negocioselectronicos.tienda;

public class Producto {
    private String cIdProdu;
    private String cNombre;
    private String cDesProdu;
    private String nPrecio;
    private String mImagen;
    private String nStock;
    private String cIdCateg;
    private String cIdUser;

    public Producto() {
    }

    public String getcIdProdu() {
        return cIdProdu;
    }

    public void setcIdProdu(String cIdProdu) {
        this.cIdProdu = cIdProdu;
    }

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public String getcDesProdu() {
        return cDesProdu;
    }

    public void setcDesProdu(String cDesProdu) {
        this.cDesProdu = cDesProdu;
    }

    public String getnPrecio() {
        return nPrecio;
    }

    public void setnPrecio(String nPrecio) {
        this.nPrecio = nPrecio;
    }

    public String getmImagen() {
        return mImagen;
    }

    public void setmImagen(String mImagen) {
        this.mImagen = mImagen;
    }

    public String getnStock() {
        return nStock;
    }

    public void setnStock(String nStock) {
        this.nStock = nStock;
    }

    public String getcIdCateg() {
        return cIdCateg;
    }

    public void setcIdCateg(String cIdCateg) {
        this.cIdCateg = cIdCateg;
    }

    public String getcIdUser() {
        return cIdUser;
    }

    public void setcIdUser(String cIdUser) {
        this.cIdUser = cIdUser;
    }
}
