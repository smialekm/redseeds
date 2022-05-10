type about_bgn.html %1\about_int.html about_end.html > %1\about.html 2> nul

@REM mkdir %1\about_files
@REM copy /y about_files\*.* %1\about_files\
@REM copy /-y about_int.html %1\about_int.html