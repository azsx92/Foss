# Foss
## 왜 파이어베이스를 사용하게 되었는가?
- 파이어베이스는 초급자인 사람도 쉽게 접근할 수 있는 서비스를 제공하면 프리티어로 가격 부담이 이 적어 많은 사용자가 이용하고 있다.
- 그래서 나는 여기서 FCM 과 Topic 서비스를 간단하게 기능을 구현할 예정이다.
## 개발 환경
- jdk 17
- springboot version : 3.2.0
- firebase admin version : 9.2.0

## ERD 설계도
- ![Foss.png](capture%2Fmysql%2FFoss.png)

- 우선 mysql -u root -p 의 비번을 잃어버려서 초기화를 하는데 정말 많은 시간을 소비하고 힘들었다.
- macOS에서 MariaDB 설치 시 발생하는 문제는 주로 잔여 파일 충돌과 시스템 권한 문제로 판단을 하였고 mariadb 를 지웠다가 다시 깔았다가 계속 안되는 문제가 발생 했다. 해결을 한것은 홈브로를 완전히 지우고 다시 마리아디비를 설치하니 문제가 해결되었다.
---
![macOs 시스템 권한주기.png](capture%2Fmysql%2FmacOs%20%EC%8B%9C%EC%8A%A4%ED%85%9C%20%EA%B6%8C%ED%95%9C%EC%A3%BC%EA%B8%B0.png)
![mysql 문제.png](capture%2Fmysql%2Fmysql%20%EB%AC%B8%EC%A0%9C.png)
# 1. 시스템 보안 설정 확인 (필수)

시스템 설정 → 개인정보 보호 및 보안 → 전체 디스크 접근 이동

+ 버튼 클릭 후 다음 항목 추가:

터미널

/usr/local/opt/mariadb/bin/mariadbd (직접 찾아서 추가) 어떻게 추가해?

재시동 후 확인

**전체 디스크 접근 권한에 mariadbd(마리아DB 데몬) 추가하는 방법은 다음과 같습니다:**

---

### **1. 전체 디스크 접근 권한 화면에서 + 버튼 클릭**

- 현재 스크린샷에서 오른쪽 하단에 **"+"** 버튼이 있습니다. 클릭하세요.

---

### **2. 파일 선택 창이 뜨면 직접 mariadbd 바이너리 경로로 이동**

- 상단의 **이동** 필드나 Finder의 **Command+Shift+G** 단축키를 눌러
  아래 경로를 입력하고 Enter:

```
/usr/local/opt/mariadb/bin/
```

- 또는 Finder에서 직접 **Macintosh HD > usr > local > opt > mariadb > bin** 폴더로 들어가세요.

---

### **3. mariadbd 선택**

- 폴더 안에서 **mariadbd** 파일을 선택한 뒤 **열기**를 클릭하세요.

---

### **4. 리스트에 mariadbd가 추가되면 토글을 켜기**

- 리스트에 **mariadbd**가 추가되면 오른쪽 토글을 활성화(파란색)로 바꿔주세요.

---

### **5. 시스템 재시동(권장)**

- 변경 사항이 바로 적용되지 않을 수 있으니, Mac을 **재시동**하세요.

---

**참고:**

- 이 작업은 mariadbd(마리아DB 서버)가 macOS의 파일시스템에 자유롭게 접근할 수 있도록 해줍니다.
- 이 작업 후에도 문제가 있다면, 같은 방식으로 **brew**(Homebrew)도 추가해보세요.

---

필요하다면, 추가로 어떤 파일을 넣어야 할지 안내해드릴 수 있습니다!
다른 궁금한 점 있으시면 언제든 질문해 주세요.

<div style="text-align: center">⁂</div>

[^1]: image.jpg

[^2]: https://discussions.apple.com/thread/255633011

[^3]: https://pimylifeup.com/macos-full-disk-access/

[^4]: https://mjtsai.com/blog/2022/09/22/terminal-and-full-disk-access/

[^5]: https://support.apple.com/en-is/101653

[^6]: https://www.youtube.com/watch?v=10XP36ZT_iM

[^7]: https://stackoverflow.com/questions/55856496/how-do-i-enable-full-disk-access-for-a-macos-app-being-built-and-debugged-in-xco

[^8]: https://mariadb.com/kb/en/launching-mariadb-on-boot-on-macos-1015-catalina/

[^9]: https://www.reddit.com/r/macsysadmin/comments/pz9zoz/give_services_full_disk_access_via_terminal_or_by/

[^10]: https://faronics.kayako.com/article/332-how-to-enable-full-disk-access-on-macos-ventura

[^11]: https://velog.io/@jinkkung/MariaDB-installation-in-MacOS

[^12]: https://support.intego.com/hc/en-us/articles/360016683471-How-to-Enable-Full-Disk-Access-in-macOS

[^13]: https://discussions.apple.com/thread/251308068

[^14]: https://n8henrie.com/2018/11/how-to-give-full-disk-access-to-a-binary-in-macos-mojave/

[^15]: https://apple.stackexchange.com/questions/430583/macos-applications-disk-access-authorizations

[^16]: https://stackoverflow.com/questions/68140577/starting-mariadb-at-boot-on-macos-catalina

[^17]: https://support.apple.com/en-lb/guide/mac-help/mchlccb25729/mac

[^18]: https://github.com/orgs/Homebrew/discussions/1741

[^19]: https://www.youtube.com/watch?v=EuzmBrmr8qE

[^20]: https://apple.stackexchange.com/questions/351632/is-there-command-to-grant-full-disk-access-for-specific-application

[^21]: https://discussions.apple.com/thread/250719819


---
## Last login: Thu May  8 19:31:08 on console


Error: No such file or directory - /usr/local/var/homebrew/linked/mariadb
➜  ~
원인이 뭐야?

MariaDB 설치 오류의 원인은 **Homebrew의 심볼릭 링크 시스템**에 있습니다. 오류 메시지 "No such file or directory - /usr/local/var/homebrew/linked/mariadb"는 Homebrew가 패키지를 추적하는 내부 시스템에 문제가 생겼음을 나타냅니다[^7].

## 문제 해결 방법

1. **Homebrew 캐시 정리**

```bash
brew cleanup --prune=all
```

2. **Homebrew 자체 복구**

```bash
brew doctor
brew update-reset
```

3. **심볼릭 링크 디렉토리 생성**

```bash
sudo mkdir -p /usr/local/var/homebrew/linked
sudo chown -R $(whoami):admin /usr/local/var/homebrew
```

4. **Homebrew 완전 재구성**

```bash
brew update
brew upgrade
```

5. **MariaDB 재설치**

```bash
brew install mariadb
brew services start mariadb
```


## 원인 분석

이 오류는 일반적으로 다음과 같은 상황에서 발생합니다:

- Homebrew의 내부 데이터베이스에는 MariaDB 참조가 있지만 실제 파일은 수동으로 삭제됨[^2][^6]
- Homebrew 디렉토리의 권한 문제[^3]
- Homebrew의 링크 시스템이 손상됨[^7]

특히 수동으로 `/usr/local/Cellar/mariadb`를 삭제했기 때문에 Homebrew의 링크 시스템이 혼란스러워진 것으로 보입니다.

재설치 후에도 문제가 계속된다면 전체 디스크 접근 권한 설정도 확인해 보세요. 스크린샷에서 볼 수 있듯이 일부 응용 프로그램에 이미 권한을 부여하셨네요[^1].

추가 질문이 있으시면 물어보세요!

<div style="text-align: center">⁂</div>

[^1]: image.jpg

[^2]: https://stackoverflow.com/questions/20448822/mariadb-homebrew-install-errors

[^3]: https://github.com/orgs/Homebrew/discussions/5514

[^4]: https://apple.stackexchange.com/questions/381546/macos-catalina-homebrew-mariadb-installation-issues

[^5]: https://mariadb.com/kb/en/installing-mariadb-on-macos-using-homebrew/

[^6]: https://time-map-installer.tistory.com/238

[^7]: https://penguin-kim.tistory.com/41

[^8]: https://mariadb.com/resources/blog/installing-mariadb-10-1-16-on-mac-os-x-with-homebrew/

[^9]: https://mariadb.com/kb/ko/installing-mariadb-on-macos-using-homebrew/

[^10]: https://velog.io/@skwx50000/mac-m1OS-Monterey-homebrew를-이용하여-mac에-mariadb설치-및-에러-수정

[^11]: https://github.com/Homebrew/homebrew-core/issues/1272

[^12]: https://laracasts.com/discuss/channels/servers/homebrew-mariadbmysql-socket-issues

[^13]: https://www.bytebase.com/reference/mariadb/how-to/how-to-install-mariadb-client-on-mac-ubuntu-centos-windows/

[^14]: https://superuser.com/questions/435442/symlink-error-when-installing-mysql-via-homebrew

[^15]: https://stackoverflow.com/questions/19398823/issue-404-trying-to-install-mariadb-via-homebrew-on-mac

[^16]: https://velog.io/@jeongs/오류발생-no-such-file-or-directory-opthomebrewbinbrew

[^17]: https://www.geuni.tech/en/macos/brew_no_such_file_or_directory

[^18]: https://delightpip.tistory.com/207

[^19]: https://github.com/orgs/Homebrew/discussions/3410

[^20]: https://stackoverflow.com/questions/54543697/not-able-to-install-anything-because-of-brew-no-such-file-or-directory-error

[^21]: https://github.com/Homebrew/legacy-homebrew/issues/31760

[^22]: https://mariadb.com/kb/en/what-to-do-if-mariadb-doesnt-start/

[^23]: https://apple.stackexchange.com/questions/304157/homebrew-mxcl-mysql2274-service-could-not-initialize-unable-to-set-current

[^24]: https://mariadb.com/kb/en/installing-mariadb-on-macos-using-homebrew/

[^25]: https://camillehdl.dev/troubleshoot-homebrew-mysql-mariadb-apple-silicon/

[^26]: https://apple.stackexchange.com/questions/459965/get-homebrew-to-re-create-executable-symlinks-for-an-installed-package-ghostscr

[^27]: https://velog.io/@cdspacenoob/Maria00

[^28]: https://www.bytebase.com/reference/mariadb/how-to/how-to-install-mariadb-client-on-mac-ubuntu-centos-windows/

[^29]: https://docs.brew.sh/Common-Issues

[^30]: https://github.com/willbryant/kitchen_sync/issues/46

[^31]: https://superuser.com/questions/925240/brew-installed-linked-but-not-found

[^32]: https://stackoverflow.com/questions/26647412/homebrew-could-not-symlink-usr-local-bin-is-not-writable

[^33]: https://github.com/orgs/Homebrew/discussions/5514

[^34]: https://www.reddit.com/r/macgaming/comments/159ng2c/unexpected_error_during_the_brew_link_step/

[^35]: https://yudeokrin.github.io/posts/2022-08-08-page19

[^36]: https://github.com/orgs/Homebrew/discussions/1741

[^37]: https://andycarter.dev/blog/resolving-post-install-issue-with-mariadb-install-via-homebrew

[^38]: https://docs.brew.sh/Troubleshooting

[^39]: https://superuser.com/questions/1731453/why-cant-i-start-mariadb-as-a-service-on-macos-with-brew

[^40]: https://laracasts.com/discuss/channels/servers/homebrew-mariadbmysql-socket-issues

[^41]: https://github.com/orgs/Homebrew/discussions/3114

[^42]: https://stackoverflow.com/questions/78866031/mariadb-on-mac-via-homebrew-mysql-impossible-after-computer-restart

[^43]: https://stackoverflow.com/questions/63421566/homebrew-mariadb-10-4-13-not-found-forced-updated-to-10-5-5-and-now-i-cannot-s

[^44]: https://velog.io/@hohomi/Trouble-Shooting-230509

[^45]: https://laracasts.com/discuss/channels/general-discussion/brew-install-mariadb-error

[^46]: https://gist.github.com/irazasyed/a74766108b4630fc5c7c822df23526e8

[^47]: https://light-tree.tistory.com/245

[^48]: https://velog.io/@thedev_junyoung/DBMysql-%EC%84%A4%EC%B9%98ERROR-2002-ERROR1045-mac

[^49]: https://www.reddit.com/r/youtubedl/comments/1f8jow3/error_no_such_file_or_directory/

[^50]: https://velog.io/@skwx50000/mac-m1OS-Monterey-homebrew를-이용하여-mac에-mariadb설치-및-에러-수정

[^51]: https://time-map-installer.tistory.com/238

