package antifraud.entity.user;

public record MaiUserResponse(Long id, String name, String username, String role)
        implements Comparable<MaiUserResponse> {
    public MaiUserResponse(MaiUser maiUser) {
        this(maiUser.getId(), maiUser.getName(), maiUser.getUsername(), maiUser.getRole().toString());
    }

    @Override
    public int compareTo(MaiUserResponse o) {
        return id.compareTo(o.id);
    }
}
