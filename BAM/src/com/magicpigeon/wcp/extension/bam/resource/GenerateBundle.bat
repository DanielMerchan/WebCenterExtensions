@REM Batch Script for creating all supported Resource Bundle Java Files from a Base
@REM Author: Daniel Merchan Garcia - Magic Pigeon Ltd
@REM Resource Bundle Name
@echo off
set baseBundleName=%1
@echo %baseBundleName%
@REM Oracle WebCenter Portal supported Languages
set lang=ar cs da de el en es fi fr fr_CA hu it iw ja ko nl no pl pt pt_BR ro ru sk sv th tr zh_CN zh_TW
@REM Iteration and creation of the files
for %%l in (%lang%) do (
	type %cd%\%baseBundleName%.java >> %cd%\%baseBundleName%_%%l.java
)
