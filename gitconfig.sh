DATE="2022.06.18"
PROJECTNAME="cursomc"
git config --global user.name 'Jose Maciel'
git config --global user.email 'josemaciel30@gmail.com'
echo "# {$PROJECTNAME}" >> README.md
git init
git branch -M main
git status
git add .
git status
git commit -m "initial commit {$DATE}"
git log
git remote add origin git@github.com:jmaciel38/cursomc.git
git push -u origin main