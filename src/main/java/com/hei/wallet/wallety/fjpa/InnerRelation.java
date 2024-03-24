package com.hei.wallet.wallety.fjpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class InnerRelation {
    private ReflectAttribute attribute;
    private String sqlDestination;
    private String destination;
}
