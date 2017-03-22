package com.taobao.muming.engineering.spi;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */

import java.util.List;

/**
 * SPI注册器
 *
 * @author 一觞
 * @date 15/12/17
 */
public interface SpiNodesRegister<T, R> {

    /**
     * SPI节点注册
     */
    void register();

    /**
     * 获取注册的SPI
     *
     * @return
     */
    List<SpiNode<T, R>> getSpiNodes();

}
