Pod::Spec.new do |spec|
    spec.name                     = 'debug_view'
    spec.version                  = '0.0.7'
    spec.homepage                 = 'https://idfinance.com/'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Debug View'
    spec.vendored_frameworks      = 'build/cocoapods/framework/behaviour_tracker.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '12.0'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':debug_view',
        'PRODUCT_MODULE_NAME' => 'behaviour_tracker',
    }
                
    spec.script_phases = [
        {
            :name => 'Build debug_view',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
    spec.resources = ['build/compose/ios/behaviour_tracker/compose-resources']
end