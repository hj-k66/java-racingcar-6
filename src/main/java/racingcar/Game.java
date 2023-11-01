package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Game {

    private final InputView inputView;
    private final OutputView outputView;

    public Game() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        List<Car> cars = inputView.inputCarName();
        int roundCnt = inputView.inputRoundCnt();
        while (roundCnt-- > 0) {
            judgeMoving(cars);
            outputView.printRound(cars);
        }
        System.out.println("최종 우승자 : " + outputView.getWinner(cars));
    }

    public void judgeMoving(List<Car> cars) {
        for (Car car : cars) {
            int randomNum = Randoms.pickNumberInRange(0, 9);
            if (randomNum >= 4) {
                car.moveForward();
            }
        }
    }
}
