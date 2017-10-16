SUMMARY = "A console development image with some C/C++ dev tools"
HOMEPAGE = "http://www.jumpnowtek.com"
LICENSE = "MIT"

IMAGE_FEATURES += "package-management splash"
IMAGE_LINGUAS = "en-us"

inherit core-image

DEPENDS += "bcm2835-bootfiles"

CORE_OS = " \
    openssh openssh-keygen openssh-sftp-server \
    term-prompt \
    tzdata \
"

WIFI_SUPPORT = " \
    crda \
    iw \
    linux-firmware-brcm43430 \
    linux-firmware-ralink \
    linux-firmware-rtl8192ce \
    linux-firmware-rtl8192cu \
    linux-firmware-rtl8192su \
    wireless-tools \
    wpa-supplicant \
"

DEV_SDK_INSTALL = " \
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    file \
    g++ \
    g++-symlinks \
    gcc \
    gcc-symlinks \
    gdb \
    gdbserver \
    gettext \
    git \
    ldd \
    libstdc++ \
    libstdc++-dev \
    libtool \
    make \
    perl-modules \
    pkgconfig \
    python-modules \
    python3-modules \
    python3-pydoc \
"

DEV_EXTRAS = " \
    ntp \
    ntp-tickadj \
    serialecho  \
    spiloop \
"

EXTRA_TOOLS_INSTALL = " \
    bzip2 \
    devmem2 \
    dosfstools \
    ethtool \
    fbset \
    findutils \
    firewall \
    i2c-tools \
    iperf3 \
    iproute2 \
    iptables \
    less \
    memtester \
    nano \
    netcat \
    procps \
    rsync \
    sysfsutils \
    tcpdump \
    unzip \
    util-linux \
    wget \
    zip \
"

CAN_TOOLS = " \
    canutils \
"

RPI_STUFF = " \
    bcm2835-tests \
    omxplayer \
    pi-blaster \
    raspi2fb \
    rpio \
    rpi-gpio \
    userland \
"

AUX_STUFF = " \
    mc \
    tcpdump \
    netcat \
    traceroute \
    ntp \
    atop \
    iftop \
    iotop \
    htop \
    powertop \
    netperf \
    valgrind \
    screen \    
"

LORA_NET = " \
    lora-gateway \
    lora-gateway-dbg \
    lora-gateway-dev \
    lora-gateway-doc \
    lora-gateway-staticdev \
    lora-pkt-fwd \
    lora-pkt-fwd-dbg \
    lora-pkt-fwd-doc \
    erlang \
    lorawan-server \
"

IMAGE_INSTALL += " \
    ${CAN_TOOLS} \
    ${CORE_OS} \
    ${DEV_SDK_INSTALL} \
    ${DEV_EXTRAS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${RPI_STUFF} \
    ${WIFI_SUPPORT} \
    ${LORA_NET} \
    ${AUX_STUFF} \
"

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
}

disable_bootlogd() {
    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
}

ROOTFS_POSTPROCESS_COMMAND += " \
    set_local_timezone ; \
    disable_bootlogd ; \
 "

export IMAGE_BASENAME = "console-image"

