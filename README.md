# Сборка проекта на windows
1. Установить Docker если не установлен.
2. Установить Maven если не установлен.
3. Создать у себя на компьютере папку.
4. Открыть эту папку в терминале, щелкнуть правой мыши внутри созданной папки, выбрать открыть в терминале.
5. Ввести команду: git clone https://github.com/RomanGorkavenko/accounting-for-media.git
6. Затем ввести команды по очереди:
   - cd .\accounting-for-media\
   - mvn clean package
   - docker-compose up
7. Дождаться пока все контейнеры в Docker запустятся, подождать еще немного пока все сервисы пройдут регистрацию.
8. Ввести ссылку в адресной строке браузера
   - http://localhost:8765/webjars/swagger-ui/index.html
![123](https://)

git clone https://github.com/RomanGorkavenko/accounting-for-media.git

cd .\accounting-for-media\

mvn clean package

docker-compose up
