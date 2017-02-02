package zombe.core.config;

import java.lang.*;
import zombe.core.util.KeyHelper;
import zombe.core.util.KeyBind;

import javax.annotation.Nonnull;

public class KeyBindConstraint extends OptionConstraint<KeyBind> {

    public KeyBindConstraint() {}

    @Nonnull
    @Override
    public KeyBind parse(@Nonnull String string) throws BadValueException {
        KeyBind key = new KeyBind(string);
        if (key.code == -1) {
            throw new NumberFormatException();
        }
        return key;
    }

    @Nonnull
    @Override
    public KeyBind defaultValue() {
        return KeyBind.NONE;
    }

    @Nonnull
    @Override
    public KeyBind getMax() {
        return KeyBind.NONE;
    }

    @Override
    public String toString(@Nonnull Object value) {
        if (value instanceof Integer) {
            return KeyHelper.getKeyName((Integer) value);
        } else if (value instanceof KeyBind) {
            KeyBind val = (KeyBind) value;
            return val.name;
        } else {
            throw new IllegalArgumentException();
        }
    }
}