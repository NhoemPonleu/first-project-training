package com.acledabankplc.security.auditing;

import java.util.Optional;

public interface AuditorAwareUserName <T>{
    Optional<T> getCurrentAuditor1();
}
