package com.example.hdvideoplayer_youtube.ModelData.VideoModel

import com.google.gson.annotations.SerializedName

data class VideoModel(

	@field:SerializedName("playerConfig")
	val playerConfig: PlayerConfig? = null,

	@field:SerializedName("attestation")
	val attestation: Attestation? = null,

	@field:SerializedName("overlay")
	val overlay: Overlay? = null,

	@field:SerializedName("streamingData")
	val streamingData: StreamingData? = null,

	@field:SerializedName("responseContext")
	val responseContext: ResponseContext? = null,

	@field:SerializedName("playabilityStatus")
	val playabilityStatus: PlayabilityStatus? = null,

	@field:SerializedName("annotations")
	val annotations: List<AnnotationsItem?>? = null,

	@field:SerializedName("playbackTracking")
	val playbackTracking: PlaybackTracking? = null,

	@field:SerializedName("captions")
	val captions: Captions? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("endscreen")
	val endscreen: Endscreen? = null,

	@field:SerializedName("videoDetails")
	val videoDetails: VideoDetails? = null,

	@field:SerializedName("frameworkUpdates")
	val frameworkUpdates: FrameworkUpdates? = null,

	@field:SerializedName("adBreakHeartbeatParams")
	val adBreakHeartbeatParams: String? = null,

	@field:SerializedName("playerSettingsMenuData")
	val playerSettingsMenuData: PlayerSettingsMenuData? = null
)

data class PlaybackTracking(

	@field:SerializedName("videostatsWatchtimeUrl")
	val videostatsWatchtimeUrl: VideostatsWatchtimeUrl? = null,

	@field:SerializedName("videostatsDelayplayUrl")
	val videostatsDelayplayUrl: VideostatsDelayplayUrl? = null,

	@field:SerializedName("videostatsScheduledFlushWalltimeSeconds")
	val videostatsScheduledFlushWalltimeSeconds: List<Int?>? = null,

	@field:SerializedName("qoeUrl")
	val qoeUrl: QoeUrl? = null,

	@field:SerializedName("videostatsPlaybackUrl")
	val videostatsPlaybackUrl: VideostatsPlaybackUrl? = null,

	@field:SerializedName("ptrackingUrl")
	val ptrackingUrl: PtrackingUrl? = null,

	@field:SerializedName("atrUrl")
	val atrUrl: AtrUrl? = null,

	@field:SerializedName("engageUrl")
	val engageUrl: EngageUrl? = null,

	@field:SerializedName("videostatsDefaultFlushIntervalSeconds")
	val videostatsDefaultFlushIntervalSeconds: Int? = null
)

data class ServerReadaheadConfig(

	@field:SerializedName("enable")
	val enable: Boolean? = null,

	@field:SerializedName("nextRequestPolicy")
	val nextRequestPolicy: NextRequestPolicy? = null
)

data class NetworkProtocolConfig(

	@field:SerializedName("useQuic")
	val useQuic: Boolean? = null
)

data class MutationsItem(

	@field:SerializedName("payload")
	val payload: Payload? = null,

	@field:SerializedName("entityKey")
	val entityKey: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class AndroidPlayerStatsConfig(

	@field:SerializedName("changeCpnOnFatalPlaybackError")
	val changeCpnOnFatalPlaybackError: Boolean? = null,

	@field:SerializedName("usePblForQoeReporting")
	val usePblForQoeReporting: Boolean? = null,

	@field:SerializedName("usePblForPlaybacktrackingReporting")
	val usePblForPlaybacktrackingReporting: Boolean? = null,

	@field:SerializedName("usePblForAttestationReporting")
	val usePblForAttestationReporting: Boolean? = null,

	@field:SerializedName("usePblForHeartbeatReporting")
	val usePblForHeartbeatReporting: Boolean? = null
)

data class PredictedReadaheadConfig(

	@field:SerializedName("maxReadaheadMs")
	val maxReadaheadMs: Int? = null,

	@field:SerializedName("minReadaheadMs")
	val minReadaheadMs: Int? = null
)

data class HeadersItem(

	@field:SerializedName("headerType")
	val headerType: String? = null
)

data class EndscreenRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("startMs")
	val startMs: String? = null,

	@field:SerializedName("elements")
	val elements: List<ElementsItem?>? = null
)

data class TaskCoordinatorConfig(

	@field:SerializedName("prefetchCoordinatorBufferedPositionMillisRelease")
	val prefetchCoordinatorBufferedPositionMillisRelease: Int? = null
)

data class UnsubscribedButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class EntityBatchUpdate(

	@field:SerializedName("mutations")
	val mutations: List<MutationsItem?>? = null,

	@field:SerializedName("timestamp")
	val timestamp: Timestamp? = null
)

data class AnnotationsItem(

	@field:SerializedName("playerAnnotationsExpandedRenderer")
	val playerAnnotationsExpandedRenderer: PlayerAnnotationsExpandedRenderer? = null
)

data class LanguageName(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Metadata(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Payload(

	@field:SerializedName("offlineabilityEntity")
	val offlineabilityEntity: OfflineabilityEntity? = null
)

data class AndroidCronetResponsePriority(

	@field:SerializedName("priorityValue")
	val priorityValue: String? = null
)

data class EngageUrl(

	@field:SerializedName("headers")
	val headers: List<HeadersItem?>? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null
)

data class OfflineabilityRenderer(

	@field:SerializedName("offlineable")
	val offlineable: Boolean? = null,

	@field:SerializedName("impressionEndpoints")
	val impressionEndpoints: List<ImpressionEndpointsItem?>? = null,

	@field:SerializedName("formats")
	val formats: List<FormatsItem?>? = null,

	@field:SerializedName("offlineabilityRendererSupportedConfigs")
	val offlineabilityRendererSupportedConfigs: OfflineabilityRendererSupportedConfigs? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class PlayerSettingsMenuData(

	@field:SerializedName("loggingDirectives")
	val loggingDirectives: LoggingDirectives? = null
)

data class LidarSdkConfig(

	@field:SerializedName("enableActiveViewReporter")
	val enableActiveViewReporter: Boolean? = null,

	@field:SerializedName("enableIsAndroidVideoAlwaysMeasurable")
	val enableIsAndroidVideoAlwaysMeasurable: Boolean? = null,

	@field:SerializedName("enableImprovedSizeReportingAndroid")
	val enableImprovedSizeReportingAndroid: Boolean? = null,

	@field:SerializedName("useMediaTime")
	val useMediaTime: Boolean? = null,

	@field:SerializedName("enableActiveViewAudioMeasurementAndroid")
	val enableActiveViewAudioMeasurementAndroid: Boolean? = null,

	@field:SerializedName("sendTosMetrics")
	val sendTosMetrics: Boolean? = null,

	@field:SerializedName("usePlayerState")
	val usePlayerState: Boolean? = null,

	@field:SerializedName("enableIosAppStateCheck")
	val enableIosAppStateCheck: Boolean? = null
)

data class Title(

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Visibility(

	@field:SerializedName("types")
	val types: String? = null
)

data class Icon(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class StreamingData(

	@field:SerializedName("formats")
	val formats: List<FormatsItem?>? = null,

	@field:SerializedName("adaptiveFormats")
	val adaptiveFormats: List<AdaptiveFormatsItem?>? = null,

	@field:SerializedName("expiresInSeconds")
	val expiresInSeconds: String? = null
)

data class SubscribeAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class WatchEndpoint(

	@field:SerializedName("watchEndpointSupportedOnesieConfig")
	val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null
)

data class OfflineStreamSelectionConfig(

	@field:SerializedName("enablePrefetchPlayerResponse")
	val enablePrefetchPlayerResponse: Boolean? = null
)

data class CmsPathProbeConfig(

	@field:SerializedName("cmsPathProbeDelayMs")
	val cmsPathProbeDelayMs: Int? = null
)

data class IndexRange(

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("end")
	val end: String? = null
)

data class InitialBandwidthEstimatesItem(

	@field:SerializedName("bandwidthBps")
	val bandwidthBps: String? = null,

	@field:SerializedName("detailedNetworkType")
	val detailedNetworkType: String? = null
)

data class PlaybackOnesieConfig(

	@field:SerializedName("playerInitConfig")
	val playerInitConfig: PlayerInitConfig? = null,

	@field:SerializedName("commonConfig")
	val commonConfig: CommonConfig? = null,

	@field:SerializedName("exoPlayerInitConfig")
	val exoPlayerInitConfig: ExoPlayerInitConfig? = null,

	@field:SerializedName("dataSaverConfig")
	val dataSaverConfig: DataSaverConfig? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class PtrackingUrl(

	@field:SerializedName("headers")
	val headers: List<HeadersItem?>? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null
)

data class Endpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null
)

data class PlayerControlsConfig(

	@field:SerializedName("showCachedInTimebar")
	val showCachedInTimebar: Boolean? = null
)

data class CommonConfig(
	val any: Any? = null
)

data class TranslationLanguagesItem(

	@field:SerializedName("languageCode")
	val languageCode: String? = null,

	@field:SerializedName("languageName")
	val languageName: LanguageName? = null
)

data class AndroidMetadataNetworkConfig(

	@field:SerializedName("coalesceRequests")
	val coalesceRequests: Boolean? = null
)

data class ButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class UnsubscribeAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class UnsubscribeEndpoint(

	@field:SerializedName("channelIds")
	val channelIds: List<String?>? = null,

	@field:SerializedName("params")
	val params: String? = null
)

data class Watermark(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class LoggingDirectives(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("visibility")
	val visibility: Visibility? = null,

	@field:SerializedName("enableDisplayloggerExperiment")
	val enableDisplayloggerExperiment: Boolean? = null
)

data class ThumbnailOverlaysItem(

	@field:SerializedName("thumbnailOverlayTimeStatusRenderer")
	val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null
)

data class DataSaverConfig(

	@field:SerializedName("simpleBitrateCap")
	val simpleBitrateCap: String? = null
)

data class PlayerGestureConfig(

	@field:SerializedName("downAndOutPortraitAllowed")
	val downAndOutPortraitAllowed: Boolean? = null,

	@field:SerializedName("downAndOutLandscapeAllowed")
	val downAndOutLandscapeAllowed: Boolean? = null
)

data class Text(

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ExoPlayerConfig(

	@field:SerializedName("enableVariableSpeedPlayback")
	val enableVariableSpeedPlayback: Boolean? = null,

	@field:SerializedName("enableVp9EncryptedIfInHardware")
	val enableVp9EncryptedIfInHardware: Boolean? = null,

	@field:SerializedName("maxVideoDurationPerFetchMs")
	val maxVideoDurationPerFetchMs: Int? = null,

	@field:SerializedName("useExoPlayer")
	val useExoPlayer: Boolean? = null,

	@field:SerializedName("liveOnlyBufferHealthHalfLifeSeconds")
	val liveOnlyBufferHealthHalfLifeSeconds: Int? = null,

	@field:SerializedName("onesieFixNonZeroStartTimeFormatSelection")
	val onesieFixNonZeroStartTimeFormatSelection: Boolean? = null,

	@field:SerializedName("ignoreLoadTimeoutForFallback")
	val ignoreLoadTimeoutForFallback: Boolean? = null,

	@field:SerializedName("maxVideoEstimatedLoadDurationMs")
	val maxVideoEstimatedLoadDurationMs: Int? = null,

	@field:SerializedName("useAdaptiveBitrate")
	val useAdaptiveBitrate: Boolean? = null,

	@field:SerializedName("useMedialibAudioTrackRendererForLive")
	val useMedialibAudioTrackRendererForLive: Boolean? = null,

	@field:SerializedName("serverBweMultiplier")
	val serverBweMultiplier: Int? = null,

	@field:SerializedName("reportExoPlayerStateOnTransition")
	val reportExoPlayerStateOnTransition: Boolean? = null,

	@field:SerializedName("cacheCheckDirectoryWritabilityOnce")
	val cacheCheckDirectoryWritabilityOnce: Boolean? = null,

	@field:SerializedName("maxResolutionForWhiteNoise")
	val maxResolutionForWhiteNoise: Int? = null,

	@field:SerializedName("liveOnlyWindowChunks")
	val liveOnlyWindowChunks: Int? = null,

	@field:SerializedName("canPlayHdDrm")
	val canPlayHdDrm: Boolean? = null,

	@field:SerializedName("whiteNoiseRenderEffectMode")
	val whiteNoiseRenderEffectMode: String? = null,

	@field:SerializedName("enableDynamicHdr")
	val enableDynamicHdr: Boolean? = null,

	@field:SerializedName("useAbruptSplicing")
	val useAbruptSplicing: Boolean? = null,

	@field:SerializedName("enableLibvpxHdr")
	val enableLibvpxHdr: Boolean? = null,

	@field:SerializedName("v2PerformEarlyStreamSelection")
	val v2PerformEarlyStreamSelection: Boolean? = null,

	@field:SerializedName("allowDroppingUndecodedFrames")
	val allowDroppingUndecodedFrames: Boolean? = null,

	@field:SerializedName("sufficientBandwidthOverhead")
	val sufficientBandwidthOverhead: Any? = null,

	@field:SerializedName("lowAudioQualityBandwidthThresholdBps")
	val lowAudioQualityBandwidthThresholdBps: Int? = null,

	@field:SerializedName("whiteNoiseScale")
	val whiteNoiseScale: Int? = null,

	@field:SerializedName("allowCacheOverrideToLowerQualitiesWithinRange")
	val allowCacheOverrideToLowerQualitiesWithinRange: Int? = null,

	@field:SerializedName("slidingPercentileScalar")
	val slidingPercentileScalar: Int? = null,

	@field:SerializedName("videoBufferSegmentCount")
	val videoBufferSegmentCount: Int? = null,

	@field:SerializedName("onesieDataSourceAboveCacheDataSource")
	val onesieDataSourceAboveCacheDataSource: Boolean? = null,

	@field:SerializedName("useTimeSeriesBufferPrediction")
	val useTimeSeriesBufferPrediction: Boolean? = null,

	@field:SerializedName("enableVpxMediaView")
	val enableVpxMediaView: Boolean? = null,

	@field:SerializedName("httpReadTimeoutMs")
	val httpReadTimeoutMs: Int? = null,

	@field:SerializedName("v2MinTimeBetweenAbrReevaluationMs")
	val v2MinTimeBetweenAbrReevaluationMs: Int? = null,

	@field:SerializedName("minimumBandwidthSampleBytes")
	val minimumBandwidthSampleBytes: Int? = null,

	@field:SerializedName("minDurationForPlaybackRestartMs")
	val minDurationForPlaybackRestartMs: Int? = null,

	@field:SerializedName("onlyVideoBandwidth")
	val onlyVideoBandwidth: Boolean? = null,

	@field:SerializedName("usePredictedBuffer")
	val usePredictedBuffer: Boolean? = null,

	@field:SerializedName("enableRedirectorHostFallback")
	val enableRedirectorHostFallback: Boolean? = null,

	@field:SerializedName("v2UsePlaybackStreamSelectionResult")
	val v2UsePlaybackStreamSelectionResult: Boolean? = null,

	@field:SerializedName("hdrMaxScreenBrightnessThreshold")
	val hdrMaxScreenBrightnessThreshold: Int? = null,

	@field:SerializedName("maxDurationForQualityDecreaseMs")
	val maxDurationForQualityDecreaseMs: Int? = null,

	@field:SerializedName("lowPoolLoad")
	val lowPoolLoad: Any? = null,

	@field:SerializedName("minDurationForPlaybackStartMs")
	val minDurationForPlaybackStartMs: Int? = null,

	@field:SerializedName("useRadioTypeForInitialQualitySelection")
	val useRadioTypeForInitialQualitySelection: Boolean? = null,

	@field:SerializedName("useAverageBitrate")
	val useAverageBitrate: Boolean? = null,

	@field:SerializedName("numAudioSegmentsPerFetch")
	val numAudioSegmentsPerFetch: Int? = null,

	@field:SerializedName("httpNonplayerLoadTimeoutMs")
	val httpNonplayerLoadTimeoutMs: Int? = null,

	@field:SerializedName("enableCacheAwareStreamSelection")
	val enableCacheAwareStreamSelection: Boolean? = null,

	@field:SerializedName("readAheadGrowthRate")
	val readAheadGrowthRate: Int? = null,

	@field:SerializedName("estimatedServerClockHalfLife")
	val estimatedServerClockHalfLife: Int? = null,

	@field:SerializedName("liveOnlyMinLatencyToSeekRatio")
	val liveOnlyMinLatencyToSeekRatio: Int? = null,

	@field:SerializedName("enableLibvpxVideoTrackRenderer")
	val enableLibvpxVideoTrackRenderer: Boolean? = null,

	@field:SerializedName("liveNetNocontentMaximumErrors")
	val liveNetNocontentMaximumErrors: Int? = null,

	@field:SerializedName("onesieVideoBufferReadTimeoutMs")
	val onesieVideoBufferReadTimeoutMs: String? = null,

	@field:SerializedName("minDurationForQualityIncreaseMs")
	val minDurationForQualityIncreaseMs: Int? = null,

	@field:SerializedName("lowAudioQualityConnTypes")
	val lowAudioQualityConnTypes: List<String?>? = null,

	@field:SerializedName("enableLibvpxFallback")
	val enableLibvpxFallback: Boolean? = null,

	@field:SerializedName("lowWatermarkMs")
	val lowWatermarkMs: Int? = null,

	@field:SerializedName("maxReadAheadMediaTimeMs")
	val maxReadAheadMediaTimeMs: Int? = null,

	@field:SerializedName("highWatermarkMs")
	val highWatermarkMs: Int? = null,

	@field:SerializedName("httpConnectTimeoutMs")
	val httpConnectTimeoutMs: Int? = null,

	@field:SerializedName("minErrorsForPcrFallback")
	val minErrorsForPcrFallback: Int? = null,

	@field:SerializedName("enableDynamicHdrInHardware")
	val enableDynamicHdrInHardware: Boolean? = null,

	@field:SerializedName("manifestlessSequenceMethod")
	val manifestlessSequenceMethod: String? = null,

	@field:SerializedName("enableMediaCodecSwHdr")
	val enableMediaCodecSwHdr: Boolean? = null,

	@field:SerializedName("minRetryCount")
	val minRetryCount: Int? = null,

	@field:SerializedName("useDashForLiveStreams")
	val useDashForLiveStreams: Boolean? = null,

	@field:SerializedName("useExoCronetDataSource")
	val useExoCronetDataSource: Boolean? = null,

	@field:SerializedName("forceWidevineL3")
	val forceWidevineL3: Boolean? = null,

	@field:SerializedName("ultralowAudioQualityBandwidthThresholdBps")
	val ultralowAudioQualityBandwidthThresholdBps: Int? = null,

	@field:SerializedName("minErrorsForRedirectorHostFallback")
	val minErrorsForRedirectorHostFallback: Int? = null,

	@field:SerializedName("enableVp9IfThresholdsPass")
	val enableVp9IfThresholdsPass: Boolean? = null,

	@field:SerializedName("logMediaRequestEventsToCsi")
	val logMediaRequestEventsToCsi: Boolean? = null,

	@field:SerializedName("blacklistFormatOnError")
	val blacklistFormatOnError: Boolean? = null,

	@field:SerializedName("ignoreUnneededSeeksToLiveHead")
	val ignoreUnneededSeeksToLiveHead: Boolean? = null,

	@field:SerializedName("enableMediaCodecHdr")
	val enableMediaCodecHdr: Boolean? = null,

	@field:SerializedName("maxFrameDropIntervalMs")
	val maxFrameDropIntervalMs: Int? = null,

	@field:SerializedName("useDashForOtfAndCompletedLiveStreams")
	val useDashForOtfAndCompletedLiveStreams: Boolean? = null,

	@field:SerializedName("liveOnlyMinBufferHealthRatio")
	val liveOnlyMinBufferHealthRatio: Any? = null,

	@field:SerializedName("bufferChunkSizeKb")
	val bufferChunkSizeKb: Int? = null,

	@field:SerializedName("slidingPercentile")
	val slidingPercentile: Any? = null,

	@field:SerializedName("predictorType")
	val predictorType: String? = null,

	@field:SerializedName("minChunksNeededToPreferOffline")
	val minChunksNeededToPreferOffline: Int? = null,

	@field:SerializedName("secondsToMaxAggressiveness")
	val secondsToMaxAggressiveness: Int? = null,

	@field:SerializedName("minReadAheadMediaTimeMs")
	val minReadAheadMediaTimeMs: Int? = null,

	@field:SerializedName("enableInfiniteNetworkLoadingRetries")
	val enableInfiniteNetworkLoadingRetries: Boolean? = null,

	@field:SerializedName("maxAllowableTimeBeforeMediaTimeUpdateSec")
	val maxAllowableTimeBeforeMediaTimeUpdateSec: Int? = null,

	@field:SerializedName("useStickyRedirectHttpDataSource")
	val useStickyRedirectHttpDataSource: Boolean? = null,

	@field:SerializedName("avoidReusePlaybackAcrossLoadvideos")
	val avoidReusePlaybackAcrossLoadvideos: Boolean? = null,

	@field:SerializedName("bearerMinDurationToRetainAfterDiscardMs")
	val bearerMinDurationToRetainAfterDiscardMs: List<Int?>? = null,

	@field:SerializedName("maxInitialByteRate")
	val maxInitialByteRate: Int? = null,

	@field:SerializedName("useExoPlayerV2")
	val useExoPlayerV2: Boolean? = null,

	@field:SerializedName("useRedirectorOnNetworkChange")
	val useRedirectorOnNetworkChange: Boolean? = null,

	@field:SerializedName("ignoreViewportSizeWhenSticky")
	val ignoreViewportSizeWhenSticky: Boolean? = null,

	@field:SerializedName("disableCacheAwareVideoFormatEvaluation")
	val disableCacheAwareVideoFormatEvaluation: Boolean? = null,

	@field:SerializedName("enableVp9EncryptedIfThresholdsPass")
	val enableVp9EncryptedIfThresholdsPass: Boolean? = null,

	@field:SerializedName("useLiveHeadWindow")
	val useLiveHeadWindow: Boolean? = null,

	@field:SerializedName("nonHardwareMediaCodecNames")
	val nonHardwareMediaCodecNames: List<String?>? = null,

	@field:SerializedName("emitVideoDecoderChangeEvents")
	val emitVideoDecoderChangeEvents: Boolean? = null,

	@field:SerializedName("enableHighlyAvailableFormatFallbackOnPcr")
	val enableHighlyAvailableFormatFallbackOnPcr: Boolean? = null,

	@field:SerializedName("httpLoadTimeoutMs")
	val httpLoadTimeoutMs: Int? = null,

	@field:SerializedName("disableLibvpxLoopFilter")
	val disableLibvpxLoopFilter: Boolean? = null,

	@field:SerializedName("serverProvidedBandwidthHeader")
	val serverProvidedBandwidthHeader: String? = null,

	@field:SerializedName("retryLiveNetNocontentWithDelay")
	val retryLiveNetNocontentWithDelay: Boolean? = null,

	@field:SerializedName("enableOpus")
	val enableOpus: Boolean? = null,

	@field:SerializedName("numVideoSegmentsPerFetchStrategy")
	val numVideoSegmentsPerFetchStrategy: String? = null,

	@field:SerializedName("slidingWindowSize")
	val slidingWindowSize: Int? = null,

	@field:SerializedName("preventVideoFrameLaggingWithLibvpx")
	val preventVideoFrameLaggingWithLibvpx: Boolean? = null,

	@field:SerializedName("recordTrackRendererTimingEvents")
	val recordTrackRendererTimingEvents: Boolean? = null,

	@field:SerializedName("enableV2Gapless")
	val enableV2Gapless: Boolean? = null,

	@field:SerializedName("drmMaxKeyfetchDelayMs")
	val drmMaxKeyfetchDelayMs: Int? = null,

	@field:SerializedName("numVideoSegmentsPerFetch")
	val numVideoSegmentsPerFetch: Int? = null,

	@field:SerializedName("useOpusMedAsLowQualityAudio")
	val useOpusMedAsLowQualityAudio: Boolean? = null,

	@field:SerializedName("manifestlessPartialChunkStrategy")
	val manifestlessPartialChunkStrategy: String? = null,

	@field:SerializedName("enableBandaidHttpDataSource")
	val enableBandaidHttpDataSource: Boolean? = null,

	@field:SerializedName("liveOnlyReadaheadStepSizeChunks")
	val liveOnlyReadaheadStepSizeChunks: Int? = null,

	@field:SerializedName("minDurationToRetainAfterDiscardMs")
	val minDurationToRetainAfterDiscardMs: Int? = null,

	@field:SerializedName("hdrMinScreenBrightness")
	val hdrMinScreenBrightness: Int? = null,

	@field:SerializedName("audioBufferSegmentCount")
	val audioBufferSegmentCount: Int? = null,

	@field:SerializedName("preferOnesieBufferedFormat")
	val preferOnesieBufferedFormat: Boolean? = null,

	@field:SerializedName("enableVp9IfInHardware")
	val enableVp9IfInHardware: Boolean? = null,

	@field:SerializedName("useDynamicReadAhead")
	val useDynamicReadAhead: Boolean? = null,

	@field:SerializedName("onesieVideoBufferLoadTimeoutMs")
	val onesieVideoBufferLoadTimeoutMs: String? = null,

	@field:SerializedName("drmMetricsQoeLoggingFraction")
	val drmMetricsQoeLoggingFraction: Any? = null,

	@field:SerializedName("highPoolLoad")
	val highPoolLoad: Any? = null,

	@field:SerializedName("waitForDrmLicenseBeforeProcessingAndroidStuckBufferfull")
	val waitForDrmLicenseBeforeProcessingAndroidStuckBufferfull: Boolean? = null,

	@field:SerializedName("estimatedServerClockStrictOffset")
	val estimatedServerClockStrictOffset: Boolean? = null,

	@field:SerializedName("useLiveDvrForDashLiveStreams")
	val useLiveDvrForDashLiveStreams: Boolean? = null,

	@field:SerializedName("whiteNoiseOffset")
	val whiteNoiseOffset: Int? = null,

	@field:SerializedName("libvpxEnableGl")
	val libvpxEnableGl: Boolean? = null,

	@field:SerializedName("cronetResetTimeoutOnRedirects")
	val cronetResetTimeoutOnRedirects: Boolean? = null,

	@field:SerializedName("enableExoplayerReuse")
	val enableExoplayerReuse: Boolean? = null,

	@field:SerializedName("useMediaTimeCappedLoadControl")
	val useMediaTimeCappedLoadControl: Boolean? = null,

	@field:SerializedName("useLiveHeadTimeMillis")
	val useLiveHeadTimeMillis: Boolean? = null,

	@field:SerializedName("useYtVodMediaSourceForV2")
	val useYtVodMediaSourceForV2: Boolean? = null,

	@field:SerializedName("enableSurfaceviewResizeWorkaround")
	val enableSurfaceviewResizeWorkaround: Boolean? = null,

	@field:SerializedName("allowTrackSelectionWithUpdatedVideoItagsForExoV2")
	val allowTrackSelectionWithUpdatedVideoItagsForExoV2: Boolean? = null,

	@field:SerializedName("liveOnlyPegStrategy")
	val liveOnlyPegStrategy: String? = null,

	@field:SerializedName("adaptiveLiveHeadWindow")
	val adaptiveLiveHeadWindow: Boolean? = null,

	@field:SerializedName("matchQualityToViewportOnUnfullscreen")
	val matchQualityToViewportOnUnfullscreen: Boolean? = null,

	@field:SerializedName("enableMaxReadaheadAbrThreshold")
	val enableMaxReadaheadAbrThreshold: Boolean? = null
)

data class BrowseEndpoint(

	@field:SerializedName("browseId")
	val browseId: String? = null
)

data class Captions(

	@field:SerializedName("playerCaptionsTracklistRenderer")
	val playerCaptionsTracklistRenderer: PlayerCaptionsTracklistRenderer? = null
)

data class RunsItem(

	@field:SerializedName("text")
	val text: String? = null
)

data class VariableSpeedConfig(

	@field:SerializedName("androidVariableSpeedTimeoutSecs")
	val androidVariableSpeedTimeoutSecs: Int? = null,

	@field:SerializedName("availablePlaybackSpeeds")
	val availablePlaybackSpeeds: List<AvailablePlaybackSpeedsItem?>? = null,

	@field:SerializedName("enableVariableSpeedOnOtf")
	val enableVariableSpeedOnOtf: Boolean? = null
)

data class ImpressionEndpointsItem(

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("pingingEndpoint")
	val pingingEndpoint: PingingEndpoint? = null,

	@field:SerializedName("loggingUrls")
	val loggingUrls: List<LoggingUrlsItem?>? = null
)

data class Endscreen(

	@field:SerializedName("endscreenRenderer")
	val endscreenRenderer: EndscreenRenderer? = null
)

data class Timestamp(

	@field:SerializedName("seconds")
	val seconds: String? = null,

	@field:SerializedName("nanos")
	val nanos: Int? = null
)

data class VideostatsDelayplayUrl(

	@field:SerializedName("headers")
	val headers: List<HeadersItem?>? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null
)

data class CommandMetadata(
	val any: Any? = null
)

data class ThumbnailOverlayTimeStatusRenderer(

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("text")
	val text: Text? = null
)

data class Attestation(

	@field:SerializedName("playerAttestationRenderer")
	val playerAttestationRenderer: PlayerAttestationRenderer? = null
)

data class AudioConfig(

	@field:SerializedName("perceptualLoudnessDb")
	val perceptualLoudnessDb: Any? = null,

	@field:SerializedName("loudnessDb")
	val loudnessDb: Any? = null,

	@field:SerializedName("enablePerFormatLoudness")
	val enablePerFormatLoudness: Boolean? = null
)

data class Accessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class SubscribeEndpoint(

	@field:SerializedName("channelIds")
	val channelIds: List<String?>? = null,

	@field:SerializedName("params")
	val params: String? = null
)

data class InitRange(

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("end")
	val end: String? = null
)

data class AdaptiveFormatsItem(

	@field:SerializedName("itag")
	val itag: Int? = null,

	@field:SerializedName("indexRange")
	val indexRange: IndexRange? = null,

	@field:SerializedName("projectionType")
	val projectionType: String? = null,

	@field:SerializedName("initRange")
	val initRange: InitRange? = null,

	@field:SerializedName("bitrate")
	val bitrate: Int? = null,

	@field:SerializedName("mimeType")
	val mimeType: String? = null,

	@field:SerializedName("audioQuality")
	val audioQuality: String? = null,

	@field:SerializedName("approxDurationMs")
	val approxDurationMs: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("audioSampleRate")
	val audioSampleRate: String? = null,

	@field:SerializedName("quality")
	val quality: String? = null,

	@field:SerializedName("audioChannels")
	val audioChannels: Int? = null,

	@field:SerializedName("contentLength")
	val contentLength: String? = null,

	@field:SerializedName("lastModified")
	val lastModified: String? = null,

	@field:SerializedName("loudnessDb")
	val loudnessDb: Any? = null,

	@field:SerializedName("averageBitrate")
	val averageBitrate: Int? = null,

	@field:SerializedName("highReplication")
	val highReplication: Boolean? = null,

	@field:SerializedName("fps")
	val fps: Int? = null,

	@field:SerializedName("qualityLabel")
	val qualityLabel: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("colorInfo")
	val colorInfo: ColorInfo? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class VideostatsPlaybackUrl(

	@field:SerializedName("headers")
	val headers: List<HeadersItem?>? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null
)

data class AccessibilityData(

	@field:SerializedName("label")
	val label: String? = null
)

data class PlayerConfig(

	@field:SerializedName("retryConfig")
	val retryConfig: RetryConfig? = null,

	@field:SerializedName("mediaCommonConfig")
	val mediaCommonConfig: MediaCommonConfig? = null,

	@field:SerializedName("networkProtocolConfig")
	val networkProtocolConfig: NetworkProtocolConfig? = null,

	@field:SerializedName("decodeQualityConfig")
	val decodeQualityConfig: DecodeQualityConfig? = null,

	@field:SerializedName("cmsPathProbeConfig")
	val cmsPathProbeConfig: CmsPathProbeConfig? = null,

	@field:SerializedName("vrConfig")
	val vrConfig: VrConfig? = null,

	@field:SerializedName("androidNetworkStackConfig")
	val androidNetworkStackConfig: AndroidNetworkStackConfig? = null,

	@field:SerializedName("variableSpeedConfig")
	val variableSpeedConfig: VariableSpeedConfig? = null,

	@field:SerializedName("stickyQualitySelectionConfig")
	val stickyQualitySelectionConfig: StickyQualitySelectionConfig? = null,

	@field:SerializedName("lidarSdkConfig")
	val lidarSdkConfig: LidarSdkConfig? = null,

	@field:SerializedName("androidMedialibConfig")
	val androidMedialibConfig: AndroidMedialibConfig? = null,

	@field:SerializedName("androidPlayerStatsConfig")
	val androidPlayerStatsConfig: AndroidPlayerStatsConfig? = null,

	@field:SerializedName("playbackStartConfig")
	val playbackStartConfig: PlaybackStartConfig? = null,

	@field:SerializedName("adRequestConfig")
	val adRequestConfig: AdRequestConfig? = null,

	@field:SerializedName("adSurveyRequestConfig")
	val adSurveyRequestConfig: AdSurveyRequestConfig? = null,

	@field:SerializedName("playerControlsConfig")
	val playerControlsConfig: PlayerControlsConfig? = null,

	@field:SerializedName("playerGestureConfig")
	val playerGestureConfig: PlayerGestureConfig? = null,

	@field:SerializedName("exoPlayerConfig")
	val exoPlayerConfig: ExoPlayerConfig? = null,

	@field:SerializedName("qoeStatsClientConfig")
	val qoeStatsClientConfig: QoeStatsClientConfig? = null,

	@field:SerializedName("taskCoordinatorConfig")
	val taskCoordinatorConfig: TaskCoordinatorConfig? = null,

	@field:SerializedName("audioConfig")
	val audioConfig: AudioConfig? = null
)

data class WatchEndpointSupportedOnesieConfig(

	@field:SerializedName("playbackOnesieConfig")
	val playbackOnesieConfig: PlaybackOnesieConfig? = null
)

data class KeepSubscriptionButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class VideoDetails(

	@field:SerializedName("isOwnerViewing")
	val isOwnerViewing: Boolean? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("isLiveContent")
	val isLiveContent: Boolean? = null,

	@field:SerializedName("keywords")
	val keywords: List<String?>? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("lengthSeconds")
	val lengthSeconds: String? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("shortDescription")
	val shortDescription: String? = null,

	@field:SerializedName("isPrivate")
	val isPrivate: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("isCrawlable")
	val isCrawlable: Boolean? = null,

	@field:SerializedName("isUnpluggedCorpus")
	val isUnpluggedCorpus: Boolean? = null,

	@field:SerializedName("allowRatings")
	val allowRatings: Boolean? = null,

	@field:SerializedName("viewCount")
	val viewCount: String? = null,

	@field:SerializedName("channelId")
	val channelId: String? = null
)

data class AudioTracksItem(

	@field:SerializedName("captionTrackIndices")
	val captionTrackIndices: List<Int?>? = null
)

data class SubscribedButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class OfflineabilityRendererSupportedConfigs(

	@field:SerializedName("offlineStreamSelectionConfig")
	val offlineStreamSelectionConfig: OfflineStreamSelectionConfig? = null
)

data class Label(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class CaptionTracksItem(

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null,

	@field:SerializedName("isTranslatable")
	val isTranslatable: Boolean? = null,

	@field:SerializedName("vssId")
	val vssId: String? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("name")
	val name: Name? = null,

	@field:SerializedName("languageCode")
	val languageCode: String? = null
)

data class ParamsItem(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class DynamicReadaheadConfig(

	@field:SerializedName("minReadAheadWatermarkMarginMs")
	val minReadAheadWatermarkMarginMs: Int? = null,

	@field:SerializedName("maxReadAheadWatermarkMarginMs")
	val maxReadAheadWatermarkMarginMs: Int? = null,

	@field:SerializedName("readAheadWatermarkMarginRatio")
	val readAheadWatermarkMarginRatio: Int? = null,

	@field:SerializedName("readAheadGrowthRateMs")
	val readAheadGrowthRateMs: Int? = null,

	@field:SerializedName("shouldIncorporateNetworkActiveState")
	val shouldIncorporateNetworkActiveState: Boolean? = null,

	@field:SerializedName("maxReadAheadMediaTimeMs")
	val maxReadAheadMediaTimeMs: Int? = null,

	@field:SerializedName("minReadAheadMediaTimeMs")
	val minReadAheadMediaTimeMs: Int? = null
)

data class PlayabilityStatus(

	@field:SerializedName("playableInEmbed")
	val playableInEmbed: Boolean? = null,

	@field:SerializedName("offlineability")
	val offlineability: Offlineability? = null,

	@field:SerializedName("contextParams")
	val contextParams: String? = null,

	@field:SerializedName("miniplayer")
	val miniplayer: Miniplayer? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class UnsubscribeButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class QoeUrl(

	@field:SerializedName("headers")
	val headers: List<HeadersItem?>? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null
)

data class LoggingUrlsItem(

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null,

	@field:SerializedName("macros")
	val macros: List<MacrosItem?>? = null
)

data class Offlineability(

	@field:SerializedName("offlineabilityRenderer")
	val offlineabilityRenderer: OfflineabilityRenderer? = null
)

data class PlayerAttestationRenderer(

	@field:SerializedName("challenge")
	val challenge: String? = null
)

data class SubscribeButtonRenderer(

	@field:SerializedName("buttonText")
	val buttonText: ButtonText? = null,

	@field:SerializedName("subscribeAccessibility")
	val subscribeAccessibility: SubscribeAccessibility? = null,

	@field:SerializedName("unsubscribeMessage")
	val unsubscribeMessage: UnsubscribeMessage? = null,

	@field:SerializedName("showPreferences")
	val showPreferences: Boolean? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("subscribedButtonText")
	val subscribedButtonText: SubscribedButtonText? = null,

	@field:SerializedName("enabled")
	val enabled: Boolean? = null,

	@field:SerializedName("unsubscribeButtonText")
	val unsubscribeButtonText: UnsubscribeButtonText? = null,

	@field:SerializedName("subscribed")
	val subscribed: Boolean? = null,

	@field:SerializedName("unsubscribeAccessibility")
	val unsubscribeAccessibility: UnsubscribeAccessibility? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("style")
	val style: Style? = null,

	@field:SerializedName("serverTimestampMs")
	val serverTimestampMs: String? = null,

	@field:SerializedName("channelId")
	val channelId: String? = null,

	@field:SerializedName("unsubscribedButtonText")
	val unsubscribedButtonText: UnsubscribedButtonText? = null,

	@field:SerializedName("serviceEndpoints")
	val serviceEndpoints: List<ServiceEndpointsItem?>? = null
)

data class NextRequestPolicy(

	@field:SerializedName("targetAudioReadaheadMs")
	val targetAudioReadaheadMs: Int? = null,

	@field:SerializedName("targetVideoReadaheadMs")
	val targetVideoReadaheadMs: Int? = null
)

data class MacrosItem(

	@field:SerializedName("macroType")
	val macroType: String? = null
)

data class Name(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class AdSurveyRequestConfig(

	@field:SerializedName("useGetRequests")
	val useGetRequests: Boolean? = null
)

data class QoeStatsClientConfig(

	@field:SerializedName("batchedEntriesPeriodMs")
	val batchedEntriesPeriodMs: String? = null
)

data class Thumbnail(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class ThumbnailsItem(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class VideostatsWatchtimeUrl(

	@field:SerializedName("headers")
	val headers: List<HeadersItem?>? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null
)

data class ExoPlayerInitConfig(

	@field:SerializedName("exoPlayerConfig")
	val exoPlayerConfig: ExoPlayerConfig? = null
)

data class MediaUstreamerRequestConfig(

	@field:SerializedName("videoPlaybackUstreamerConfig")
	val videoPlaybackUstreamerConfig: String? = null,

	@field:SerializedName("isVideoPlaybackRequestIdempotent")
	val isVideoPlaybackRequestIdempotent: Boolean? = null,

	@field:SerializedName("enableVideoPlaybackRequest")
	val enableVideoPlaybackRequest: Boolean? = null,

	@field:SerializedName("videoPlaybackPostEmptyBody")
	val videoPlaybackPostEmptyBody: Boolean? = null
)

data class Overlay(
	val any: Any? = null
)

data class Style(

	@field:SerializedName("styleType")
	val styleType: String? = null,

	@field:SerializedName("suppressFreeIcon")
	val suppressFreeIcon: Boolean? = null
)

data class CallToAction(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class VrConfig(

	@field:SerializedName("showHqButton")
	val showHqButton: Boolean? = null,

	@field:SerializedName("magicWindowEduOverlayText")
	val magicWindowEduOverlayText: String? = null,

	@field:SerializedName("magicWindowEduOverlayAnimationUrl")
	val magicWindowEduOverlayAnimationUrl: String? = null,

	@field:SerializedName("sphericalDirectionLoggingEnabled")
	val sphericalDirectionLoggingEnabled: Boolean? = null,

	@field:SerializedName("enableAndroidVr180MagicWindow")
	val enableAndroidVr180MagicWindow: Boolean? = null,

	@field:SerializedName("allowVr")
	val allowVr: Boolean? = null,

	@field:SerializedName("allowSubtitles")
	val allowSubtitles: Boolean? = null,

	@field:SerializedName("enableMagicWindowZoom")
	val enableMagicWindowZoom: Boolean? = null,

	@field:SerializedName("enableAndroidMagicWindowEduOverlay")
	val enableAndroidMagicWindowEduOverlay: Boolean? = null
)

data class PlayerAnnotationsExpandedRenderer(

	@field:SerializedName("featuredChannel")
	val featuredChannel: FeaturedChannel? = null,

	@field:SerializedName("allowSwipeDismiss")
	val allowSwipeDismiss: Boolean? = null
)

data class DecodeQualityConfig(

	@field:SerializedName("maximumVideoDecodeVerticalResolution")
	val maximumVideoDecodeVerticalResolution: Int? = null
)

data class ElementsItem(

	@field:SerializedName("endscreenElementRenderer")
	val endscreenElementRenderer: EndscreenElementRenderer? = null
)

data class HovercardButton(

	@field:SerializedName("subscribeButtonRenderer")
	val subscribeButtonRenderer: SubscribeButtonRenderer? = null
)

data class MediaCommonConfig(

	@field:SerializedName("mediaFetchMaximumServerErrors")
	val mediaFetchMaximumServerErrors: Int? = null,

	@field:SerializedName("mediaFetchMaximumNetworkErrors")
	val mediaFetchMaximumNetworkErrors: Int? = null,

	@field:SerializedName("serverReadaheadConfig")
	val serverReadaheadConfig: ServerReadaheadConfig? = null,

	@field:SerializedName("useServerDrivenAbr")
	val useServerDrivenAbr: Boolean? = null,

	@field:SerializedName("dynamicReadaheadConfig")
	val dynamicReadaheadConfig: DynamicReadaheadConfig? = null,

	@field:SerializedName("mediaFetchRetryConfig")
	val mediaFetchRetryConfig: MediaFetchRetryConfig? = null,

	@field:SerializedName("mediaFetchMaximumErrors")
	val mediaFetchMaximumErrors: Int? = null,

	@field:SerializedName("predictedReadaheadConfig")
	val predictedReadaheadConfig: PredictedReadaheadConfig? = null,

	@field:SerializedName("mediaUstreamerRequestConfig")
	val mediaUstreamerRequestConfig: MediaUstreamerRequestConfig? = null
)

data class AtrUrl(

	@field:SerializedName("headers")
	val headers: List<HeadersItem?>? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null,

	@field:SerializedName("elapsedMediaTimeSeconds")
	val elapsedMediaTimeSeconds: Int? = null
)

data class ServiceEndpointsItem(

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("unsubscribeEndpoint")
	val unsubscribeEndpoint: UnsubscribeEndpoint? = null,

	@field:SerializedName("subscribeEndpoint")
	val subscribeEndpoint: SubscribeEndpoint? = null
)

data class Miniplayer(

	@field:SerializedName("miniplayerRenderer")
	val miniplayerRenderer: MiniplayerRenderer? = null
)

data class PlayerCaptionsTracklistRenderer(

	@field:SerializedName("audioTracks")
	val audioTracks: List<AudioTracksItem?>? = null,

	@field:SerializedName("captionTracks")
	val captionTracks: List<CaptionTracksItem?>? = null,

	@field:SerializedName("translationLanguages")
	val translationLanguages: List<TranslationLanguagesItem?>? = null,

	@field:SerializedName("defaultTranslationSourceTrackIndices")
	val defaultTranslationSourceTrackIndices: List<Int?>? = null,

	@field:SerializedName("defaultAudioTrackIndex")
	val defaultAudioTrackIndex: Int? = null
)

data class PlaybackStartConfig(

	@field:SerializedName("startTimeToleranceBeforeMs")
	val startTimeToleranceBeforeMs: String? = null
)

data class AvailablePlaybackSpeedsItem(

	@field:SerializedName("label")
	val label: Label? = null,

	@field:SerializedName("value")
	val value: Any? = null
)

data class OfflineabilityEntity(

	@field:SerializedName("contentCheckOk")
	val contentCheckOk: Boolean? = null,

	@field:SerializedName("loggingDirectives")
	val loggingDirectives: LoggingDirectives? = null,

	@field:SerializedName("offlineabilityRenderer")
	val offlineabilityRenderer: String? = null,

	@field:SerializedName("racyCheckOk")
	val racyCheckOk: Boolean? = null,

	@field:SerializedName("addToOfflineButtonState")
	val addToOfflineButtonState: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class FrameworkUpdates(

	@field:SerializedName("entityBatchUpdate")
	val entityBatchUpdate: EntityBatchUpdate? = null
)

data class PaidChannelUnsubscribeMessageRenderer(

	@field:SerializedName("keepSubscriptionButtonText")
	val keepSubscriptionButtonText: KeepSubscriptionButtonText? = null,

	@field:SerializedName("unsubscribeMessage")
	val unsubscribeMessage: UnsubscribeMessage? = null,

	@field:SerializedName("unsubscriptionAllowed")
	val unsubscriptionAllowed: Boolean? = null,

	@field:SerializedName("unsubscribeButtonText")
	val unsubscribeButtonText: UnsubscribeButtonText? = null
)

data class PingingEndpoint(

	@field:SerializedName("hack")
	val hack: Boolean? = null
)

data class AdRequestConfig(

	@field:SerializedName("preskipPaddingAndroid")
	val preskipPaddingAndroid: Int? = null,

	@field:SerializedName("filterTimeEventsOnDelta")
	val filterTimeEventsOnDelta: Int? = null,

	@field:SerializedName("userCriticalExecOnAdsProcessing")
	val userCriticalExecOnAdsProcessing: Boolean? = null,

	@field:SerializedName("preskipScalingFactorAndroid")
	val preskipScalingFactorAndroid: Any? = null,

	@field:SerializedName("useCriticalExecOnAdsPrep")
	val useCriticalExecOnAdsPrep: Boolean? = null,

	@field:SerializedName("enableCountdownNextToThumbnailAndroid")
	val enableCountdownNextToThumbnailAndroid: Boolean? = null
)

data class EndscreenElementRenderer(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("startMs")
	val startMs: String? = null,

	@field:SerializedName("endMs")
	val endMs: String? = null,

	@field:SerializedName("aspectRatio")
	val aspectRatio: Float? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("thumbnailOverlays")
	val thumbnailOverlays: List<ThumbnailOverlaysItem?>? = null,

	@field:SerializedName("endpoint")
	val endpoint: Endpoint? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("top")
	val top: Any? = null,

	@field:SerializedName("left")
	val left: Any? = null,

	@field:SerializedName("width")
	val width: Any? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("dismiss")
	val dismiss: Dismiss? = null,

	@field:SerializedName("isSubscribe")
	val isSubscribe: Boolean? = null,

	@field:SerializedName("callToAction")
	val callToAction: CallToAction? = null,

	@field:SerializedName("hovercardButton")
	val hovercardButton: HovercardButton? = null
)

data class Image(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class UnsubscribeMessage(

	@field:SerializedName("paidChannelUnsubscribeMessageRenderer")
	val paidChannelUnsubscribeMessageRenderer: PaidChannelUnsubscribeMessageRenderer? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ColorInfo(

	@field:SerializedName("primaries")
	val primaries: String? = null,

	@field:SerializedName("matrixCoefficients")
	val matrixCoefficients: String? = null,

	@field:SerializedName("transferCharacteristics")
	val transferCharacteristics: String? = null
)

data class ServiceTrackingParamsItem(

	@field:SerializedName("service")
	val service: String? = null,

	@field:SerializedName("params")
	val params: List<ParamsItem?>? = null
)

data class MiniplayerRenderer(

	@field:SerializedName("playbackMode")
	val playbackMode: String? = null
)

data class RetryConfig(

	@field:SerializedName("retryWithLibvpx")
	val retryWithLibvpx: Boolean? = null,

	@field:SerializedName("fallbackToSwDecoderOnFormatDecodeError")
	val fallbackToSwDecoderOnFormatDecodeError: Boolean? = null,

	@field:SerializedName("l3FallbackOnDrmErrors")
	val l3FallbackOnDrmErrors: Boolean? = null,

	@field:SerializedName("widevineL3EnforcedFallbackOnDrmErrors")
	val widevineL3EnforcedFallbackOnDrmErrors: Boolean? = null,

	@field:SerializedName("retryWithNewSurfaceAttempts")
	val retryWithNewSurfaceAttempts: Int? = null,

	@field:SerializedName("retryUnderSameConditionAttempts")
	val retryUnderSameConditionAttempts: Int? = null,

	@field:SerializedName("suppressFatalErrorAfterStop")
	val suppressFatalErrorAfterStop: Boolean? = null,

	@field:SerializedName("retryAfterCacheRemoval")
	val retryAfterCacheRemoval: Boolean? = null,

	@field:SerializedName("maxPlayerRetriesWhenNetworkUnavailable")
	val maxPlayerRetriesWhenNetworkUnavailable: Int? = null,

	@field:SerializedName("retryEligibleErrors")
	val retryEligibleErrors: List<String?>? = null,

	@field:SerializedName("progressiveFallbackOnNonNetworkErrors")
	val progressiveFallbackOnNonNetworkErrors: Boolean? = null,

	@field:SerializedName("exoProxyableFormatFallback")
	val exoProxyableFormatFallback: Boolean? = null,

	@field:SerializedName("fallbackFromHfrToSfrOnFormatDecodeError")
	val fallbackFromHfrToSfrOnFormatDecodeError: Boolean? = null
)

data class AndroidMedialibConfig(

	@field:SerializedName("dashManifestVersion")
	val dashManifestVersion: Int? = null,

	@field:SerializedName("isItag18MainProfile")
	val isItag18MainProfile: Boolean? = null,

	@field:SerializedName("enablePrerollPrebuffer")
	val enablePrerollPrebuffer: Boolean? = null,

	@field:SerializedName("hpqViewportSizeFraction")
	val hpqViewportSizeFraction: Any? = null,

	@field:SerializedName("prebufferOptimizeForViewportSize")
	val prebufferOptimizeForViewportSize: Boolean? = null,

	@field:SerializedName("viewportSizeFraction")
	val viewportSizeFraction: Any? = null,

	@field:SerializedName("initialBandwidthEstimates")
	val initialBandwidthEstimates: List<InitialBandwidthEstimatesItem?>? = null
)

data class MediaFetchRetryConfig(

	@field:SerializedName("jitterFactor")
	val jitterFactor: Any? = null,

	@field:SerializedName("backoffFactor")
	val backoffFactor: Any? = null,

	@field:SerializedName("initialDelayMs")
	val initialDelayMs: Int? = null,

	@field:SerializedName("maximumDelayMs")
	val maximumDelayMs: Int? = null
)

data class StickyQualitySelectionConfig(

	@field:SerializedName("expirationTimeSinceLastManualVideoQualitySelectionMs")
	val expirationTimeSinceLastManualVideoQualitySelectionMs: String? = null,

	@field:SerializedName("stickyCeilingOverridesSimpleBitrateCap")
	val stickyCeilingOverridesSimpleBitrateCap: Boolean? = null,

	@field:SerializedName("stickySelectionType")
	val stickySelectionType: String? = null,

	@field:SerializedName("expirationTimeSinceLastPlaybackStartMs")
	val expirationTimeSinceLastPlaybackStartMs: String? = null
)

data class ResponseContext(

	@field:SerializedName("serviceTrackingParams")
	val serviceTrackingParams: List<ServiceTrackingParamsItem?>? = null,

	@field:SerializedName("maxAgeSeconds")
	val maxAgeSeconds: Int? = null,

	@field:SerializedName("visitorData")
	val visitorData: String? = null
)

data class PlayerInitConfig(

	@field:SerializedName("stickyQualitySelectionConfig")
	val stickyQualitySelectionConfig: StickyQualitySelectionConfig? = null
)

data class FormatsItem(

	@field:SerializedName("itag")
	val itag: Int? = null,

	@field:SerializedName("fps")
	val fps: Int? = null,

	@field:SerializedName("projectionType")
	val projectionType: String? = null,

	@field:SerializedName("bitrate")
	val bitrate: Int? = null,

	@field:SerializedName("mimeType")
	val mimeType: String? = null,

	@field:SerializedName("audioQuality")
	val audioQuality: String? = null,

	@field:SerializedName("approxDurationMs")
	val approxDurationMs: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("audioSampleRate")
	val audioSampleRate: String? = null,

	@field:SerializedName("quality")
	val quality: String? = null,

	@field:SerializedName("qualityLabel")
	val qualityLabel: String? = null,

	@field:SerializedName("audioChannels")
	val audioChannels: Int? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("lastModified")
	val lastModified: String? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("contentLength")
	val contentLength: String? = null,

	@field:SerializedName("averageBitrate")
	val averageBitrate: Int? = null,

	@field:SerializedName("availabilityType")
	val availabilityType: String? = null,

	@field:SerializedName("savedSettingShouldExpire")
	val savedSettingShouldExpire: Boolean? = null,

	@field:SerializedName("name")
	val name: Name? = null,

	@field:SerializedName("formatType")
	val formatType: String? = null
)

data class AndroidNetworkStackConfig(

	@field:SerializedName("networkStack")
	val networkStack: String? = null,

	@field:SerializedName("androidMetadataNetworkConfig")
	val androidMetadataNetworkConfig: AndroidMetadataNetworkConfig? = null,

	@field:SerializedName("androidCronetResponsePriority")
	val androidCronetResponsePriority: AndroidCronetResponsePriority? = null
)

data class FeaturedChannel(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("watermark")
	val watermark: Watermark? = null,

	@field:SerializedName("startTimeMs")
	val startTimeMs: String? = null,

	@field:SerializedName("endTimeMs")
	val endTimeMs: String? = null
)

data class Dismiss(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)
