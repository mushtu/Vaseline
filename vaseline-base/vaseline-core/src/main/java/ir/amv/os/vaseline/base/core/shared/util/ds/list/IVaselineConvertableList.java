package ir.amv.os.vaseline.base.core.shared.util.ds.list;

import java.util.List;

/**
 * Created by AMV on 2/24/2016.
 */
public interface IVaselineConvertableList<E> extends List<E> {

    IVaselineConvertableList<E> createConvertedList();
}
