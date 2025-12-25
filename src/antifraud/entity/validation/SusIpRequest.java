package antifraud.entity.validation;

import jakarta.validation.constraints.NotEmpty;

public record SusIpRequest(@NotEmpty String ip) {
    public SusIpRequest {
        if (ip == null || ip.isEmpty()) {
            throw new IllegalArgumentException("IP address must not be empty");
        }
        try {
            String[] parts = ip.split("\\.");
            if (parts.length != 4) throw new IllegalArgumentException();
            for (String part : parts) {
                int val = Integer.parseInt(part);
                if (val < 0 || val > 255) throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid IP address");
        }
    }
}
