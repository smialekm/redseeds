; redseeds.nsi
;

;--------------------------------

; The name of the installer
Name "ReDSeeDS Engine"

; The file to write
OutFile "redseeds-setup.exe"

; The default installation directory
InstallDir $PROGRAMFILES\ReDSeeDS

; Registry key to check for directory (so if you install again, it will 
; overwrite the old one automatically)
InstallDirRegKey HKLM "Software\ReDSeeDS" "Install_Dir"

BrandingText "ReDSeeDS Engine Installer"
;--------------------------------

; Pages

Page components
Page directory
Page instfiles

UninstPage uninstConfirm
UninstPage instfiles

;--------------------------------

; The stuff to install
Section "ReDSeeDS Engine"

  SectionIn RO
  
  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put file there
  File /r configuration
  File /r features
  File /r jre
  File /r plugins
  File .eclipseproduct
  File redseeds.ini
  File jgwnl.properties
  File redseeds.exe
  File rsl.candidates.xml
  File rsl.compare.xml
  File rsl.matching.xml
  
  ;copy EA dll
  SetOutPath $SYSDIR
  File SSJavaCOM.dll
  
  SetOutPath $INSTDIR
  
  ; Write the installation path into the registry
  WriteRegStr HKLM SOFTWARE\ReDSeeDS "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ReDSeeDS" "DisplayName" "ReDSeeDS Engine"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ReDSeeDS" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ReDSeeDS" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ReDSeeDS" "NoRepair" 1
  WriteUninstaller "uninstall.exe"
  
SectionEnd

; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts"

  CreateDirectory "$SMPROGRAMS\ReDSeeDS"
  CreateShortCut "$SMPROGRAMS\ReDSeeDS\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0
  CreateShortCut "$SMPROGRAMS\ReDSeeDS\ReDSeeDS Engine.lnk" "$INSTDIR\redseeds.exe" "" "$INSTDIR\redseeds.exe" 0
  
SectionEnd

;--------------------------------

; Uninstaller

Section "Uninstall"
  
  ; Remove registry keys
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ReDSeeDS"
  DeleteRegKey HKLM SOFTWARE\ReDSeeDS

  ; Remove files and uninstaller
  RMDir /r $INSTDIR\configuration
  RMDir /r $INSTDIR\features
  RMDir /r $INSTDIR\jre
  RMDir /r $INSTDIR\plugins
  Delete $INSTDIR\.eclipseproduct
  Delete $INSTDIR\redseeds.ini
  Delete $INSTDIR\jgwnl.properties
  Delete $INSTDIR\redseeds.exe
  Delete $INSTDIR\uninstall.exe
  Delete $INSTDIR\rsl.candidates.xml
  Delete $INSTDIR\rsl.compare.xml
  Delete $INSTDIR\rsl.matching.xml
  Delete $SYSDIR\SSJavaCOM.dll

  ; Remove shortcuts, if any
  Delete "$SMPROGRAMS\ReDSeeDS\*.*"

  ; Remove directories used
  RMDir "$SMPROGRAMS\ReDSeeDS"
  RMDir "$INSTDIR"

SectionEnd
