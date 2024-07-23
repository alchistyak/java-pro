package tech.inno.data;

public class Product {
    private Long id;
    private String account;
    private Long balance;
    private String type;
    private User user;

    public Product(User user, Long id, String account, Long balance, String type) {
        this.user = user;
        this.id = id;
        this.account = account;
        this.balance = balance;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public Long getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
    }
}
