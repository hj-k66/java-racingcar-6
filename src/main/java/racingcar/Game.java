package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public void run() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        String[] names = input.split(",");
        List<Car> cars = validateCarName(names);
        System.out.println("시도할 회수는 몇회인가요?");
        int tryCnt = Integer.parseInt(Console.readLine());
        while (tryCnt-- > 0) {
            judgeMoving(cars);
            //출력
            for (Car car : cars) {
                car.printResult();
            }
            System.out.println();
        }
        System.out.println("최종 우승자 : " + getWinner(cars));


    }

    public String getWinner(List<Car> cars){
        List<String> winners = new ArrayList<>();
        int maxMovingCnt = getMaxMovingCnt(cars);
        for(Car car : cars){
            if(car.getMovingCnt() == maxMovingCnt){
                winners.add(car.getName());
            }
        }
        return String.join(",",winners);
    }

    public int getMaxMovingCnt(List<Car> cars){
        return cars.stream()
                .max(Car::compareTo)
                .get().getMovingCnt();
    }


    public void judgeMoving(List<Car> cars) {
        for (Car car : cars) {
            int randomNum = Randoms.pickNumberInRange(0, 9);
            if (randomNum >= 4) {
                car.moveForward();
            }
        }
    }

    public List<Car> validateCarName(String[] names) {
        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
            }
            Car car = new Car(name);
            cars.add(car);
        }
        return cars;
    }

}
