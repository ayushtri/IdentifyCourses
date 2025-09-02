@echo off
cd /d "C:\Users\2421230\OneDrive - Cognizant\Desktop\IdentifyCourses"

echo Deleting old screenshots...
rmdir /s /q "test-output\screenshots"

echo Running Maven Tests...
call mvn clean test

echo Generating Allure Report...
call allure generate target/allure-results -o test-output/allure-report --clean

echo Opening Allure Report...
call allure open test-output/allure-report

pause
