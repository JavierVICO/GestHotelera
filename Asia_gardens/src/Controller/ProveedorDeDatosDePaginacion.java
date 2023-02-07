package Controller;

import java.util.ArrayList;
import java.util.List;

public interface ProveedorDeDatosDePaginacion<T> {
    int getTotalRowCount();
    List <T> getRows(int startIndex, int endIndex);
}
