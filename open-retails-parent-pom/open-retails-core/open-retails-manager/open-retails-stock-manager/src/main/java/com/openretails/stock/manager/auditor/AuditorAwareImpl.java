package com.openretails.stock.manager.auditor;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Long getCurrentAuditor() {
		return 1l;
	}
}
