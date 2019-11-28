with (import <nixpkgs>{});

let
  mavenBuild = buildMaven ./project-info.json;
  build = pkgs.lib.overrideDerivation mavenBuild.build (oldAttrs: {
    buildPhase = ''
      mvn --offline --settings ${mavenBuild.settings} compile
      mvn --version
    '';
    installPhase = ''
      mkdir $out
      mvn --offline --settings ${mavenBuild.settings} package
      mv target/*.jar $out/
      ${jdk}/bin/jar -i $out/*.jar
    '';
  });

in stdenv.mkDerivation rec {
  name = "netty-spring-harmony-${version}";
  version = "1.0.0";
  src = ./.;
  buildCommand = ''
    mkdir -p $out/{lib,bin}
    cp ${build}/*.jar $out/lib/
  '';
}
