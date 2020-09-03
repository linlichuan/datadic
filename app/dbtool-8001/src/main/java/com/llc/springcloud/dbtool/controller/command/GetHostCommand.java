package com.llc.springcloud.dbtool.controller.command;

import com.netflix.hystrix.HystrixCommand;

public class GetHostCommand extends HystrixCommand<Integer> {

    protected GetHostCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Integer run() throws Exception {
        return null;
    }
}
