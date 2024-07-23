package tech.inno.dto;

public class ProductDto {
    private Long id;
    private String account;
    private Long balance;
    private String type;
    private Long userid;

    public ProductDto() {}

    public ProductDto(Long id, String account, Long balance, String type, Long userid) {
        this.id = id;
        this.account = account;
        this.balance = balance;
        this.type = type;
        this.userid = userid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                ", userid=" + userid +
                '}';
    }
}
