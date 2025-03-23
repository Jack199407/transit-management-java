package transit.management.transferobjects;

public class User {
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private Byte roleType;

    private User(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.roleType = builder.roleType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Byte getRoleType() {
        return roleType;
    }

    public static class Builder {
        private Integer id;
        private String userName;
        private String email;
        private String password;
        private Byte roleType;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName == null ? null : userName.trim();
            return this;
        }

        public Builder email(String email) {
            this.email = email == null ? null : email.trim();
            return this;
        }

        public Builder password(String password) {
            this.password = password == null ? null : password.trim();
            return this;
        }

        public Builder roleType(Byte roleType) {
            this.roleType = roleType;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
