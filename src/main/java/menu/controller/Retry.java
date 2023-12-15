package menu.controller;

import java.util.function.Supplier;
import menu.view.OutputView;

public class Retry {

    public static <T> T execute(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
