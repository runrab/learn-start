package com.runrab.gmall.api.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author runrab
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DauCount {
    private int[] dh;
    private List<Long> yesCount;
    private List<Long> dayCount;
    private long yesTotalCount;
    private long dayTotalCount;
    private double  yesTradeTotal;
    private double  dayTradeTotal;
    private long yesTradeCount;
    private long dayTradeCount;
}
