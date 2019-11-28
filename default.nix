with (import <nixpkgs>{});

let
  mavenBuild = buildMaven ./project-info.json;
  build = lib.overrideDerivation mavenBuild.build (oldAttrs: {
    buildPhase = ''
      mvn --offline --settings ${mavenBuild.settings} compile
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
  buildInputs = [ makeWrapper ];
  src = ./.;
  buildCommand = ''
    mkdir -p $out/{lib,bin}
    cp ${build}/*.jar $out/lib/
    makeWrapper ${jre}/bin/java \
      $out/bin/run.sh --add-flags \
      "-jar $out/lib/*.jar"
  '';
}
