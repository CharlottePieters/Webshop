package domain.model;

public enum Role {
    ADMIN, CUSTOMER;

    @Override
    public String toString(){
        if (this.equals(ADMIN)){
            return "admin";
        }
        else {
            return "customer";
        }
    }
}
