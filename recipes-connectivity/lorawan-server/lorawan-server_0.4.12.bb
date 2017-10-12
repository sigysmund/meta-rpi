DESCRIPTION = "Compact server for private LoRa networks"
HOMEPAGE = "https://gotthardp.github.io/lorawan-server/"
SECTION = "console/utils"
# https://github.com/joaohf/meta-erlang
DEPENDS = "erlang-native rebar3 nodejs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/sigysmund/lorawan-server.git;branch=master \
   file://lorawan-server.init \
   file://lorawan-server.default"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

RDEPENDS_${PN} += "bash nodejs erlang erlang-compiler erlang-syntax-tools erlang-crypto \
    erlang-inets erlang-asn1 erlang-public-key erlang-ssl erlang-mnesia erlang-os-mon"

inherit useradd update-rc.d npm

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir /var/lib/lorawan-server --create-home lorawan"

INITSCRIPT_NAME = "lorawan-server"
INITSCRIPT_PARAMS = "defaults 80 30"

do_compile() {
    rebar3 release
}

do_install() {
    mkdir -p ${D}${libdir}
    cp -r ${S}/_build/default/rel/lorawan-server ${D}${libdir}

    install -d ${D}${sysconfdir}/default
    install -m 0644 ${WORKDIR}/lorawan-server.default ${D}${sysconfdir}/default/lorawan-server
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lorawan-server.init ${D}${sysconfdir}/init.d/lorawan-server
}

CONFFILES_${PN} = "${sysconfdir}/default/lorawan-server ${libdir}/lorawan-server/releases/${PV}/sys.config"
