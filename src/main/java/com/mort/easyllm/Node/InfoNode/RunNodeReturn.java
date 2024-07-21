package com.mort.easyllm.Node.InfoNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RunNodeReturn {

    private String deliverMessage;

    private InfoNode nextNode;

}
