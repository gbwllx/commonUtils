package com.taobao.muming.engineering.spi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public interface IterateProvider<T, R> extends SpiNodesRegister<T, R> {

    /**
     * 互斥执行业务识别函数
     *
     * @param input
     * @return
     * @throws Exception
     */
    default List<R> execute(T input) throws Exception {
        List<R> results = new ArrayList<>();

        List<SpiNode<T, R>> spiNodes = this.getSpiNodes();
        for (Iterator<SpiNode<T, R>> iterator = spiNodes.iterator(); iterator.hasNext(); ) {
            SpiNode<T, R> spiNode = iterator.next();

            if (spiNode.recognize(input)) {
                R ret = spiNode.apply(input);

                if (null != ret) {
                    results.add(ret);
                }
            }
        }

        return results;
    }
}
