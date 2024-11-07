package com.alexanco.api.iso8583;

import org.jpos.iso.BcdPrefixer;
import org.jpos.iso.HEXInterpreter;
import org.jpos.iso.ISOStringFieldPackager;
import org.jpos.iso.NullPadder;

public class IFB_FLLLHEX extends ISOStringFieldPackager{
    public IFB_FLLLHEX() {
        super(NullPadder.INSTANCE, HEXInterpreter.RIGHT_PADDED_F, BcdPrefixer.LLL);
    }
    /**
     * @param len - field len
     * @param description symbolic descrption
     */
    public IFB_FLLLHEX(int len, String description, boolean isLeftPadded) {
        super(len, description, NullPadder.INSTANCE,
                isLeftPadded ? HEXInterpreter.LEFT_PADDED : HEXInterpreter.RIGHT_PADDED_F,
                		BcdPrefixer.LLL);
        checkLength(len, 999);
    }
    
    @Override
    public void setLength(int len)
    {
        checkLength(len, 999);
        super.setLength(len);
    }
    
    /** Must override ISOFieldPackager method to set the Interpreter correctly */
    public void setPad (boolean pad)
    {
        setInterpreter(pad ? HEXInterpreter.LEFT_PADDED : HEXInterpreter.RIGHT_PADDED_F);
        this.pad = pad;
    }
}

