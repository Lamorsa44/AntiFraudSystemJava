package antifraud.entity.user;

public record UserDeleteResponse(String username, String status) {
    public UserDeleteResponse(MaiUser maiUser) {
        this(maiUser.getUsername(), "Deleted successfully!");
    }
}
