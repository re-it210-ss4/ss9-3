package dto;

public class ShopDto {
    private String contactName;
    private String shopName;
    private String taxId;

    public String getContactName() { return contactName; }
    public void setContactName(String n) { this.contactName = n; }
    public String getShopName() { return shopName; }
    public void setShopName(String s) { this.shopName = s; }
    public String getTaxId() { return taxId; }
    public void setTaxId(String t) { this.taxId = t; }
}