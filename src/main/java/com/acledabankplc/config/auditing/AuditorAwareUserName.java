package com.acledabankplc.config.auditing;

import java.util.Optional;

public interface AuditorAwareUserName <T>{
    Optional<T> getCurrentAuditor1();
}
