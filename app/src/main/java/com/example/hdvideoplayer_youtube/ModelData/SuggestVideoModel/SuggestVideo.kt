package com.example.hdvideoplayer_youtube.ModelData.SuggestVideoModel

import com.google.gson.annotations.SerializedName

data class SuggestVideo(

	@field:SerializedName("pageVisualEffects")
	val pageVisualEffects: List<PageVisualEffectsItem?>? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("contents")
	val contents: Contents? = null,

	@field:SerializedName("currentVideoEndpoint")
	val currentVideoEndpoint: CurrentVideoEndpoint? = null,

	@field:SerializedName("frameworkUpdates")
	val frameworkUpdates: FrameworkUpdates? = null,

	@field:SerializedName("responseContext")
	val responseContext: ResponseContext? = null,

	@field:SerializedName("playerOverlays")
	val playerOverlays: PlayerOverlays? = null,

	@field:SerializedName("topbar")
	val topbar: Topbar? = null,

	@field:SerializedName("engagementPanels")
	val engagementPanels: List<EngagementPanelsItem?>? = null
)

data class SubscribeEndpoint(

	@field:SerializedName("channelIds")
	val channelIds: List<String?>? = null,

	@field:SerializedName("params")
	val params: String? = null
)

data class AddToMenu(

	@field:SerializedName("menuRenderer")
	val menuRenderer: MenuRenderer? = null
)

data class SubscribedButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class PrefetchHintConfig(

	@field:SerializedName("prefetchPriority")
	val prefetchPriority: Int? = null,

	@field:SerializedName("countdownUiRelativeSecondsPrefetchCondition")
	val countdownUiRelativeSecondsPrefetchCondition: Int? = null
)

data class NavigationEndpointsItem(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null
)

data class SubscriberCountText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class OnDisabledCommand(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("setSettingEndpoint")
	val setSettingEndpoint: SetSettingEndpoint? = null
)

data class ExampleQuery1(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class VideoActions(

	@field:SerializedName("menuRenderer")
	val menuRenderer: MenuRenderer? = null
)

data class UnsubscribedButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class HotkeyDialog(

	@field:SerializedName("hotkeyDialogRenderer")
	val hotkeyDialogRenderer: HotkeyDialogRenderer? = null
)

data class NextEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null
)

data class StyleRunsItem(

	@field:SerializedName("startIndex")
	val startIndex: Int? = null,

	@field:SerializedName("styleRunExtensions")
	val styleRunExtensions: StyleRunExtensions? = null,

	@field:SerializedName("length")
	val length: Int? = null
)

data class OnEnabledCommand(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("setSettingEndpoint")
	val setSettingEndpoint: SetSettingEndpoint? = null
)

data class DarkColorPalette(

	@field:SerializedName("iconInactiveColor")
	val iconInactiveColor: Int? = null,

	@field:SerializedName("iconDisabledColor")
	val iconDisabledColor: Int? = null,

	@field:SerializedName("section2Color")
	val section2Color: Int? = null
)

data class UntoggledServiceEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null,

	@field:SerializedName("playlistEditEndpoint")
	val playlistEditEndpoint: PlaylistEditEndpoint? = null
)

data class FrameworkUpdates(

	@field:SerializedName("entityBatchUpdate")
	val entityBatchUpdate: EntityBatchUpdate? = null
)

data class Icon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class ThumbnailOverlaysItem(

	@field:SerializedName("thumbnailOverlayNowPlayingRenderer")
	val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRenderer? = null,

	@field:SerializedName("thumbnailOverlayToggleButtonRenderer")
	val thumbnailOverlayToggleButtonRenderer: ThumbnailOverlayToggleButtonRenderer? = null,

	@field:SerializedName("thumbnailOverlayTimeStatusRenderer")
	val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null,

	@field:SerializedName("thumbnailOverlayHoverTextRenderer")
	val thumbnailOverlayHoverTextRenderer: ThumbnailOverlayHoverTextRenderer? = null,

	@field:SerializedName("thumbnailOverlayBottomPanelRenderer")
	val thumbnailOverlayBottomPanelRenderer: ThumbnailOverlayBottomPanelRenderer? = null
)

data class PlayerOverlayRenderer(

	@field:SerializedName("addToMenu")
	val addToMenu: AddToMenu? = null,

	@field:SerializedName("shareButton")
	val shareButton: ShareButton? = null,

	@field:SerializedName("autonavToggle")
	val autonavToggle: AutonavToggle? = null,

	@field:SerializedName("videoDetails")
	val videoDetails: VideoDetails? = null,

	@field:SerializedName("endScreen")
	val endScreen: EndScreen? = null,

	@field:SerializedName("autoplay")
	val autoplay: Autoplay? = null
)

data class SignalServiceEndpoint(

	@field:SerializedName("signal")
	val signal: String? = null,

	@field:SerializedName("actions")
	val actions: List<ActionsItem?>? = null
)

data class ContextualInfo(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ShareButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class SetSettingEndpoint(

	@field:SerializedName("boolValue")
	val boolValue: Boolean? = null,

	@field:SerializedName("settingItemIdForClient")
	val settingItemIdForClient: String? = null,

	@field:SerializedName("settingItemId")
	val settingItemId: String? = null
)

data class ThumbnailText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ShortBylineText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ShowLessText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class Menu(

	@field:SerializedName("menuRenderer")
	val menuRenderer: MenuRenderer? = null,

	@field:SerializedName("sortFilterSubMenuRenderer")
	val sortFilterSubMenuRenderer: SortFilterSubMenuRenderer? = null
)

data class ItemSectionRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("contents")
	val contents: List<ContentsItem?>? = null,

	@field:SerializedName("sectionIdentifier")
	val sectionIdentifier: String? = null
)

data class MenuServiceItemRenderer(

	@field:SerializedName("hasSeparator")
	val hasSeparator: Boolean? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("serviceEndpoint")
	val serviceEndpoint: ServiceEndpoint? = null,

	@field:SerializedName("isSelected")
	val isSelected: Boolean? = null
)

data class CloseButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class SecondaryResults(

	@field:SerializedName("secondaryResults")
	val secondaryResults: SecondaryResults? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class CommentsEntryPointTeaserRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("teaserAvatar")
	val teaserAvatar: TeaserAvatar? = null,

	@field:SerializedName("teaserContent")
	val teaserContent: TeaserContent? = null
)

data class OnSubscribeEndpointsItem(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("subscribeEndpoint")
	val subscribeEndpoint: SubscribeEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class A11ySkipNavigationButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class TopbarMenuButtonRenderer(

	@field:SerializedName("menuRequest")
	val menuRequest: MenuRequest? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null,

	@field:SerializedName("style")
	val style: String? = null
)

data class TopLevelButtonsItem(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null,

	@field:SerializedName("segmentedLikeDislikeButtonRenderer")
	val segmentedLikeDislikeButtonRenderer: SegmentedLikeDislikeButtonRenderer? = null
)

data class MenuItem(

	@field:SerializedName("menuServiceItemRenderer")
	val menuServiceItemRenderer: MenuServiceItemRenderer? = null
)

data class ShowMoreText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class SectionSubtitle(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class ToggledStyle(

	@field:SerializedName("styleType")
	val styleType: String? = null
)

data class PermissionsHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class OnShowCommandsItem(

	@field:SerializedName("scrollToEngagementPanelCommand")
	val scrollToEngagementPanelCommand: ScrollToEngagementPanelCommand? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class PauseText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class Byline(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ShortViewCountText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class WatchNextEndScreenRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class AttributedDescription(

	@field:SerializedName("styleRuns")
	val styleRuns: List<StyleRunsItem?>? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class SignInEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("modalEndpoint")
	val modalEndpoint: ModalEndpoint? = null,

	@field:SerializedName("nextEndpoint")
	val nextEndpoint: NextEndpoint? = null,

	@field:SerializedName("continueAction")
	val continueAction: String? = null,

	@field:SerializedName("idamTag")
	val idamTag: String? = null,

	@field:SerializedName("hack")
	val hack: Boolean? = null
)

data class AnimationConfig(

	@field:SerializedName("minImageUpdateIntervalMs")
	val minImageUpdateIntervalMs: Int? = null,

	@field:SerializedName("crossfadeDurationMs")
	val crossfadeDurationMs: Int? = null,

	@field:SerializedName("maxFrameRate")
	val maxFrameRate: Int? = null,

	@field:SerializedName("crossfadeStartOffset")
	val crossfadeStartOffset: Int? = null
)

data class ToggleButtonSupportedData(

	@field:SerializedName("toggleButtonIdData")
	val toggleButtonIdData: ToggleButtonIdData? = null
)

data class AddToPlaylistCommand(

	@field:SerializedName("onCreateListCommand")
	val onCreateListCommand: OnCreateListCommand? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("openMiniplayer")
	val openMiniplayer: Boolean? = null,

	@field:SerializedName("openListPanel")
	val openListPanel: Boolean? = null,

	@field:SerializedName("listType")
	val listType: String? = null,

	@field:SerializedName("videoIds")
	val videoIds: List<String?>? = null
)

data class FactoidRenderer(

	@field:SerializedName("label")
	val label: Label? = null,

	@field:SerializedName("value")
	val value: Value? = null,

	@field:SerializedName("accessibilityText")
	val accessibilityText: String? = null
)

data class VideoDescriptionHeaderRenderer(

	@field:SerializedName("channelNavigationEndpoint")
	val channelNavigationEndpoint: ChannelNavigationEndpoint? = null,

	@field:SerializedName("channelThumbnail")
	val channelThumbnail: ChannelThumbnail? = null,

	@field:SerializedName("factoid")
	val factoid: List<FactoidItem?>? = null,

	@field:SerializedName("channel")
	val channel: Channel? = null,

	@field:SerializedName("publishDate")
	val publishDate: PublishDate? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("views")
	val views: Views? = null
)

data class Accessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("label")
	val label: String? = null
)

data class SecondaryNavigationEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null
)

data class NotificationPreferenceButton(

	@field:SerializedName("subscriptionNotificationToggleButtonRenderer")
	val subscriptionNotificationToggleButtonRenderer: SubscriptionNotificationToggleButtonRenderer? = null
)

data class Config(

	@field:SerializedName("webSearchboxConfig")
	val webSearchboxConfig: WebSearchboxConfig? = null,

	@field:SerializedName("colorSourceHeightMultiplier")
	val colorSourceHeightMultiplier: Int? = null,

	@field:SerializedName("applyClientImageBlur")
	val applyClientImageBlur: Boolean? = null,

	@field:SerializedName("animationConfig")
	val animationConfig: AnimationConfig? = null,

	@field:SerializedName("lightThemeBackgroundColor")
	val lightThemeBackgroundColor: Long? = null,

	@field:SerializedName("maxBottomColorSourceHeight")
	val maxBottomColorSourceHeight: Int? = null,

	@field:SerializedName("colorSourceSizeMultiplier")
	val colorSourceSizeMultiplier: Any? = null,

	@field:SerializedName("colorSourceWidthMultiplier")
	val colorSourceWidthMultiplier: Any? = null,

	@field:SerializedName("blurStrength")
	val blurStrength: Int? = null,

	@field:SerializedName("darkThemeBackgroundColor")
	val darkThemeBackgroundColor: Long? = null,

	@field:SerializedName("bottomColorSourceHeightMultiplier")
	val bottomColorSourceHeightMultiplier: Any? = null
)

data class UnsubscribeEndpoint(

	@field:SerializedName("channelIds")
	val channelIds: List<String?>? = null,

	@field:SerializedName("params")
	val params: String? = null
)

data class ExitButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class RichThumbnail(

	@field:SerializedName("movingThumbnailRenderer")
	val movingThumbnailRenderer: MovingThumbnailRenderer? = null
)

data class DefaultNavigationEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("modalEndpoint")
	val modalEndpoint: ModalEndpoint? = null
)

data class TopbarLogoRenderer(

	@field:SerializedName("endpoint")
	val endpoint: Endpoint? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("iconImage")
	val iconImage: IconImage? = null,

	@field:SerializedName("tooltipText")
	val tooltipText: TooltipText? = null,

	@field:SerializedName("overrideEntityKey")
	val overrideEntityKey: String? = null
)

data class ConfirmButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class VssLoggingContext(

	@field:SerializedName("serializedContextData")
	val serializedContextData: String? = null
)

data class LikeButton(

	@field:SerializedName("toggleButtonRenderer")
	val toggleButtonRenderer: ToggleButtonRenderer? = null
)

data class StatesItem(

	@field:SerializedName("nextStateId")
	val nextStateId: Int? = null,

	@field:SerializedName("stateId")
	val stateId: Int? = null,

	@field:SerializedName("state")
	val state: State? = null
)

data class PlayerOverlays(

	@field:SerializedName("playerOverlayRenderer")
	val playerOverlayRenderer: PlayerOverlayRenderer? = null
)

data class ForwardButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ContentRenderer(

	@field:SerializedName("commentsEntryPointTeaserRenderer")
	val commentsEntryPointTeaserRenderer: CommentsEntryPointTeaserRenderer? = null
)

data class Timestamp(

	@field:SerializedName("seconds")
	val seconds: String? = null,

	@field:SerializedName("nanos")
	val nanos: Int? = null
)

data class Topbar(

	@field:SerializedName("desktopTopbarRenderer")
	val desktopTopbarRenderer: DesktopTopbarRenderer? = null
)

data class SectionListRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("contents")
	val contents: List<ContentsItem?>? = null
)

data class SectionTitle(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class MetadataRowContainer(

	@field:SerializedName("metadataRowContainerRenderer")
	val metadataRowContainerRenderer: MetadataRowContainerRenderer? = null
)

data class ShowMoreCommand(

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("commandExecutorCommand")
	val commandExecutorCommand: CommandExecutorCommand? = null
)

data class CommentCount(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class EngagementPanelSectionListRenderer(

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("visibility")
	val visibility: String? = null,

	@field:SerializedName("onShowCommands")
	val onShowCommands: List<OnShowCommandsItem?>? = null,

	@field:SerializedName("loggingDirectives")
	val loggingDirectives: LoggingDirectives? = null,

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("veType")
	val veType: Int? = null,

	@field:SerializedName("panelIdentifier")
	val panelIdentifier: String? = null,

	@field:SerializedName("content")
	val content: Content? = null
)

data class ChangeEngagementPanelVisibilityAction(

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("visibility")
	val visibility: String? = null
)

data class ResponseContext(

	@field:SerializedName("serviceTrackingParams")
	val serviceTrackingParams: List<ServiceTrackingParamsItem?>? = null,

	@field:SerializedName("webResponseContextExtensionData")
	val webResponseContextExtensionData: WebResponseContextExtensionData? = null,

	@field:SerializedName("visitorData")
	val visitorData: String? = null,

	@field:SerializedName("mainAppWebResponseContext")
	val mainAppWebResponseContext: MainAppWebResponseContext? = null
)

data class PublishDate(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class ThumbnailsItem(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class DisabledSubtext(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Results(

	@field:SerializedName("results")
	val results: Results? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("contents")
	val contents: List<ContentsItem?>? = null
)

data class VoiceSearchDialogRenderer(

	@field:SerializedName("microphoneButtonAriaLabel")
	val microphoneButtonAriaLabel: MicrophoneButtonAriaLabel? = null,

	@field:SerializedName("promptMicrophoneLabel")
	val promptMicrophoneLabel: PromptMicrophoneLabel? = null,

	@field:SerializedName("promptHeader")
	val promptHeader: PromptHeader? = null,

	@field:SerializedName("connectionErrorMicrophoneLabel")
	val connectionErrorMicrophoneLabel: ConnectionErrorMicrophoneLabel? = null,

	@field:SerializedName("disabledSubtext")
	val disabledSubtext: DisabledSubtext? = null,

	@field:SerializedName("permissionsHeader")
	val permissionsHeader: PermissionsHeader? = null,

	@field:SerializedName("exampleQuery1")
	val exampleQuery1: ExampleQuery1? = null,

	@field:SerializedName("exitButton")
	val exitButton: ExitButton? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("microphoneOffPromptHeader")
	val microphoneOffPromptHeader: MicrophoneOffPromptHeader? = null,

	@field:SerializedName("exampleQuery2")
	val exampleQuery2: ExampleQuery2? = null,

	@field:SerializedName("connectionErrorHeader")
	val connectionErrorHeader: ConnectionErrorHeader? = null,

	@field:SerializedName("placeholderHeader")
	val placeholderHeader: PlaceholderHeader? = null,

	@field:SerializedName("disabledHeader")
	val disabledHeader: DisabledHeader? = null,

	@field:SerializedName("loadingHeader")
	val loadingHeader: LoadingHeader? = null,

	@field:SerializedName("permissionsSubtext")
	val permissionsSubtext: PermissionsSubtext? = null
)

data class Owner(

	@field:SerializedName("videoOwnerRenderer")
	val videoOwnerRenderer: VideoOwnerRenderer? = null
)

data class Subtitle(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class UntoggledAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class MenuPopupRenderer(

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class MutationsItem(

	@field:SerializedName("payload")
	val payload: Payload? = null,

	@field:SerializedName("entityKey")
	val entityKey: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("options")
	val options: Options? = null
)

data class Logo(

	@field:SerializedName("topbarLogoRenderer")
	val topbarLogoRenderer: TopbarLogoRenderer? = null
)

data class ShowEngagementPanelEndpoint(

	@field:SerializedName("panelIdentifier")
	val panelIdentifier: String? = null
)

data class SortFilterSubMenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("subMenuItems")
	val subMenuItems: List<SubMenuItemsItem?>? = null
)

data class VibrantColorPalette(

	@field:SerializedName("iconInactiveColor")
	val iconInactiveColor: Int? = null
)

data class HotkeyDialogRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("dismissButton")
	val dismissButton: DismissButton? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("sections")
	val sections: List<SectionsItem?>? = null
)

data class VideoCountShortText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class MovingThumbnailRenderer(

	@field:SerializedName("enableOverlay")
	val enableOverlay: Boolean? = null,

	@field:SerializedName("enableHoveredLogging")
	val enableHoveredLogging: Boolean? = null,

	@field:SerializedName("movingThumbnailDetails")
	val movingThumbnailDetails: MovingThumbnailDetails? = null
)

data class Visibility(

	@field:SerializedName("types")
	val types: String? = null
)

data class MicrophoneButtonAriaLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class PlaylistEditEndpoint(

	@field:SerializedName("playlistId")
	val playlistId: String? = null,

	@field:SerializedName("actions")
	val actions: List<ActionsItem?>? = null
)

data class Modal(

	@field:SerializedName("modalWithTitleAndButtonRenderer")
	val modalWithTitleAndButtonRenderer: ModalWithTitleAndButtonRenderer? = null
)

data class HotkeyDialogSectionRenderer(

	@field:SerializedName("options")
	val options: List<OptionsItem?>? = null,

	@field:SerializedName("title")
	val title: Title? = null
)

data class AdsEngagementPanelContentRenderer(

	@field:SerializedName("hack")
	val hack: Boolean? = null
)

data class VideoOwnerRenderer(

	@field:SerializedName("badges")
	val badges: List<BadgesItem?>? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("subscriptionButton")
	val subscriptionButton: SubscriptionButton? = null,

	@field:SerializedName("subscriberCountText")
	val subscriberCountText: SubscriberCountText? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class HeaderText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class EndScreenVideoRenderer(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("publishedTimeText")
	val publishedTimeText: PublishedTimeText? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("shortViewCountText")
	val shortViewCountText: ShortViewCountText? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("shortBylineText")
	val shortBylineText: ShortBylineText? = null,

	@field:SerializedName("thumbnailOverlays")
	val thumbnailOverlays: List<ThumbnailOverlaysItem?>? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null,

	@field:SerializedName("lengthText")
	val lengthText: LengthText? = null,

	@field:SerializedName("lengthInSeconds")
	val lengthInSeconds: Int? = null
)

data class OpenPopupAction(

	@field:SerializedName("popupType")
	val popupType: String? = null,

	@field:SerializedName("popup")
	val popup: Popup? = null,

	@field:SerializedName("beReused")
	val beReused: Boolean? = null
)

data class SuperTitleLink(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ItemsItem(

	@field:SerializedName("menuServiceItemRenderer")
	val menuServiceItemRenderer: MenuServiceItemRenderer? = null,

	@field:SerializedName("menuNavigationItemRenderer")
	val menuNavigationItemRenderer: MenuNavigationItemRenderer? = null,

	@field:SerializedName("videoDescriptionInfocardsSectionRenderer")
	val videoDescriptionInfocardsSectionRenderer: VideoDescriptionInfocardsSectionRenderer? = null,

	@field:SerializedName("expandableVideoDescriptionBodyRenderer")
	val expandableVideoDescriptionBodyRenderer: ExpandableVideoDescriptionBodyRenderer? = null,

	@field:SerializedName("videoDescriptionHeaderRenderer")
	val videoDescriptionHeaderRenderer: VideoDescriptionHeaderRenderer? = null
)

data class ViewCount(

	@field:SerializedName("videoViewCountRenderer")
	val videoViewCountRenderer: VideoViewCountRenderer? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class LoadingHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class SecondaryIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class CommandMetadata(

	@field:SerializedName("webCommandMetadata")
	val webCommandMetadata: WebCommandMetadata? = null
)

data class Payload(

	@field:SerializedName("subscriptionStateEntity")
	val subscriptionStateEntity: SubscriptionStateEntity? = null
)

data class PlayerOverlayAutoplayRenderer(

	@field:SerializedName("countDownSecs")
	val countDownSecs: Int? = null,

	@field:SerializedName("countDownSecsForFullscreen")
	val countDownSecsForFullscreen: Int? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("videoTitle")
	val videoTitle: VideoTitle? = null,

	@field:SerializedName("pauseText")
	val pauseText: PauseText? = null,

	@field:SerializedName("thumbnailOverlays")
	val thumbnailOverlays: List<ThumbnailOverlaysItem?>? = null,

	@field:SerializedName("nextButton")
	val nextButton: NextButton? = null,

	@field:SerializedName("preferImmediateRedirect")
	val preferImmediateRedirect: Boolean? = null,

	@field:SerializedName("cancelButton")
	val cancelButton: CancelButton? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("publishedTimeText")
	val publishedTimeText: PublishedTimeText? = null,

	@field:SerializedName("background")
	val background: Background? = null,

	@field:SerializedName("closeButton")
	val closeButton: CloseButton? = null,

	@field:SerializedName("shortViewCountText")
	val shortViewCountText: ShortViewCountText? = null,

	@field:SerializedName("byline")
	val byline: Byline? = null,

	@field:SerializedName("webShowBigThumbnailEndscreen")
	val webShowBigThumbnailEndscreen: Boolean? = null,

	@field:SerializedName("webShowNewAutonavCountdown")
	val webShowNewAutonavCountdown: Boolean? = null
)

data class ContinuationEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("continuationCommand")
	val continuationCommand: ContinuationCommand? = null,

	@field:SerializedName("getTranscriptEndpoint")
	val getTranscriptEndpoint: GetTranscriptEndpoint? = null
)

data class ExampleQuery2(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class UnifiedSharePanelRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("showLoadingSpinner")
	val showLoadingSpinner: Boolean? = null
)

data class AutoplaySwitchButtonRenderer(

	@field:SerializedName("enabledAccessibilityData")
	val enabledAccessibilityData: EnabledAccessibilityData? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("onDisabledCommand")
	val onDisabledCommand: OnDisabledCommand? = null,

	@field:SerializedName("disabledAccessibilityData")
	val disabledAccessibilityData: DisabledAccessibilityData? = null,

	@field:SerializedName("enabled")
	val enabled: Boolean? = null,

	@field:SerializedName("onEnabledCommand")
	val onEnabledCommand: OnEnabledCommand? = null
)

data class BackButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ShowReloadUiCommand(

	@field:SerializedName("targetId")
	val targetId: String? = null
)

data class CompactVideoRenderer(

	@field:SerializedName("lengthText")
	val lengthText: LengthText? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("channelThumbnail")
	val channelThumbnail: ChannelThumbnail? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("shortBylineText")
	val shortBylineText: ShortBylineText? = null,

	@field:SerializedName("menu")
	val menu: Menu? = null,

	@field:SerializedName("thumbnailOverlays")
	val thumbnailOverlays: List<ThumbnailOverlaysItem?>? = null,

	@field:SerializedName("richThumbnail")
	val richThumbnail: RichThumbnail? = null,

	@field:SerializedName("longBylineText")
	val longBylineText: LongBylineText? = null,

	@field:SerializedName("badges")
	val badges: List<BadgesItem?>? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("publishedTimeText")
	val publishedTimeText: PublishedTimeText? = null,

	@field:SerializedName("viewCountText")
	val viewCountText: ViewCountText? = null,

	@field:SerializedName("ownerBadges")
	val ownerBadges: List<OwnerBadgesItem?>? = null,

	@field:SerializedName("shortViewCountText")
	val shortViewCountText: ShortViewCountText? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class State(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ScrollToEngagementPanelCommand(

	@field:SerializedName("targetId")
	val targetId: String? = null
)

data class VideoSecondaryInfoRenderer(

	@field:SerializedName("owner")
	val owner: Owner? = null,

	@field:SerializedName("attributedDescription")
	val attributedDescription: AttributedDescription? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("metadataRowContainer")
	val metadataRowContainer: MetadataRowContainer? = null,

	@field:SerializedName("showMoreText")
	val showMoreText: ShowMoreText? = null,

	@field:SerializedName("showLessCommand")
	val showLessCommand: ShowLessCommand? = null,

	@field:SerializedName("subscribeButton")
	val subscribeButton: SubscribeButton? = null,

	@field:SerializedName("showLessText")
	val showLessText: ShowLessText? = null,

	@field:SerializedName("showMoreCommand")
	val showMoreCommand: ShowMoreCommand? = null,

	@field:SerializedName("descriptionCollapsedLines")
	val descriptionCollapsedLines: Int? = null,

	@field:SerializedName("defaultExpanded")
	val defaultExpanded: Boolean? = null
)

data class WebSearchboxConfig(

	@field:SerializedName("hasOnscreenKeyboard")
	val hasOnscreenKeyboard: Boolean? = null,

	@field:SerializedName("requestLanguage")
	val requestLanguage: String? = null,

	@field:SerializedName("requestDomain")
	val requestDomain: String? = null,

	@field:SerializedName("focusSearchbox")
	val focusSearchbox: Boolean? = null
)

data class GradientColorConfigItem(

	@field:SerializedName("startLocation")
	val startLocation: Int? = null,

	@field:SerializedName("darkThemeColor")
	val darkThemeColor: Long? = null
)

data class DisabledAccessibilityData(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class NotificationActionRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("responseText")
	val responseText: ResponseText? = null
)

data class OnUnsubscribeEndpointsItem(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null
)

data class VideoViewCountRenderer(

	@field:SerializedName("originalViewCount")
	val originalViewCount: String? = null,

	@field:SerializedName("shortViewCount")
	val shortViewCount: ShortViewCount? = null,

	@field:SerializedName("viewCount")
	val viewCount: ViewCount? = null
)

data class ResultsItem(

	@field:SerializedName("continuationItemRenderer")
	val continuationItemRenderer: ContinuationItemRenderer? = null,

	@field:SerializedName("compactRadioRenderer")
	val compactRadioRenderer: CompactRadioRenderer? = null,

	@field:SerializedName("compactVideoRenderer")
	val compactVideoRenderer: CompactVideoRenderer? = null,

	@field:SerializedName("endScreenVideoRenderer")
	val endScreenVideoRenderer: EndScreenVideoRenderer? = null
)

data class SubscribeButton(

	@field:SerializedName("subscribeButtonRenderer")
	val subscribeButtonRenderer: SubscribeButtonRenderer? = null
)

data class ThumbnailOverlayTimeStatusRenderer(

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null
)

data class ThumbnailOverlayBottomPanelRenderer(

	@field:SerializedName("icon")
	val icon: Icon? = null
)

data class ContentsItem(

	@field:SerializedName("itemSectionRenderer")
	val itemSectionRenderer: ItemSectionRenderer? = null,

	@field:SerializedName("videoSecondaryInfoRenderer")
	val videoSecondaryInfoRenderer: VideoSecondaryInfoRenderer? = null,

	@field:SerializedName("videoPrimaryInfoRenderer")
	val videoPrimaryInfoRenderer: VideoPrimaryInfoRenderer? = null,

	@field:SerializedName("commentsEntryPointHeaderRenderer")
	val commentsEntryPointHeaderRenderer: CommentsEntryPointHeaderRenderer? = null,

	@field:SerializedName("continuationItemRenderer")
	val continuationItemRenderer: ContinuationItemRenderer? = null
)

data class ShortViewCount(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class PermissionsSubtext(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class DislikeButton(

	@field:SerializedName("toggleButtonRenderer")
	val toggleButtonRenderer: ToggleButtonRenderer? = null
)

data class DialogMessagesItem(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Autoplay(

	@field:SerializedName("playerOverlayAutoplayRenderer")
	val playerOverlayAutoplayRenderer: PlayerOverlayAutoplayRenderer? = null,

	@field:SerializedName("autoplay")
	val autoplay: Autoplay? = null,

	@field:SerializedName("countDownSecs")
	val countDownSecs: Int? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("sets")
	val sets: List<SetsItem?>? = null
)

data class VoiceSearchButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ChannelThumbnail(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class Contents(

	@field:SerializedName("twoColumnWatchNextResults")
	val twoColumnWatchNextResults: TwoColumnWatchNextResults? = null
)

data class MainAppWebResponseContext(

	@field:SerializedName("trackingParam")
	val trackingParam: String? = null,

	@field:SerializedName("loggedOut")
	val loggedOut: Boolean? = null
)

data class VideoTitle(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class VideoDetails(

	@field:SerializedName("playerOverlayVideoDetailsRenderer")
	val playerOverlayVideoDetailsRenderer: PlayerOverlayVideoDetailsRenderer? = null
)

data class Html5PlaybackOnesieConfig(

	@field:SerializedName("commonConfig")
	val commonConfig: CommonConfig? = null
)

data class ModalEndpoint(

	@field:SerializedName("modal")
	val modal: Modal? = null
)

data class Content(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("continuationItemRenderer")
	val continuationItemRenderer: ContinuationItemRenderer? = null,

	@field:SerializedName("sectionListRenderer")
	val sectionListRenderer: SectionListRenderer? = null,

	@field:SerializedName("structuredDescriptionContentRenderer")
	val structuredDescriptionContentRenderer: StructuredDescriptionContentRenderer? = null,

	@field:SerializedName("adsEngagementPanelContentRenderer")
	val adsEngagementPanelContentRenderer: AdsEngagementPanelContentRenderer? = null
)

data class AutonavToggle(

	@field:SerializedName("autoplaySwitchButtonRenderer")
	val autoplaySwitchButtonRenderer: AutoplaySwitchButtonRenderer? = null
)

data class PromptHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class NextButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ParamsItem(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class OptionsItem(

	@field:SerializedName("hotkeyDialogSectionOptionRenderer")
	val hotkeyDialogSectionOptionRenderer: HotkeyDialogSectionOptionRenderer? = null
)

data class PageVisualEffectsItem(

	@field:SerializedName("cinematicContainerRenderer")
	val cinematicContainerRenderer: CinematicContainerRenderer? = null
)

data class NavigationEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null,

	@field:SerializedName("shareEntityServiceEndpoint")
	val shareEntityServiceEndpoint: ShareEntityServiceEndpoint? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null,

	@field:SerializedName("signInEndpoint")
	val signInEndpoint: SignInEndpoint? = null,

	@field:SerializedName("modalEndpoint")
	val modalEndpoint: ModalEndpoint? = null
)

data class Header(

	@field:SerializedName("engagementPanelTitleHeaderRenderer")
	val engagementPanelTitleHeaderRenderer: EngagementPanelTitleHeaderRenderer? = null
)

data class CreatorVideosButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ConfirmDialogRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("cancelButton")
	val cancelButton: CancelButton? = null,

	@field:SerializedName("confirmButton")
	val confirmButton: ConfirmButton? = null,

	@field:SerializedName("dialogMessages")
	val dialogMessages: List<DialogMessagesItem?>? = null,

	@field:SerializedName("primaryIsCancel")
	val primaryIsCancel: Boolean? = null
)

data class Searchbox(

	@field:SerializedName("fusionSearchboxRenderer")
	val fusionSearchboxRenderer: FusionSearchboxRenderer? = null
)

data class VideoDescriptionInfocardsSectionRenderer(

	@field:SerializedName("creatorVideosButton")
	val creatorVideosButton: CreatorVideosButton? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("sectionTitle")
	val sectionTitle: SectionTitle? = null,

	@field:SerializedName("channelEndpoint")
	val channelEndpoint: ChannelEndpoint? = null,

	@field:SerializedName("channelAvatar")
	val channelAvatar: ChannelAvatar? = null,

	@field:SerializedName("creatorAboutButton")
	val creatorAboutButton: CreatorAboutButton? = null,

	@field:SerializedName("sectionSubtitle")
	val sectionSubtitle: SectionSubtitle? = null
)

data class MetadataRowContainerRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("collapsedItemCount")
	val collapsedItemCount: Int? = null
)

data class EndScreen(

	@field:SerializedName("watchNextEndScreenRenderer")
	val watchNextEndScreenRenderer: WatchNextEndScreenRenderer? = null
)

data class LoggingContext(

	@field:SerializedName("vssLoggingContext")
	val vssLoggingContext: VssLoggingContext? = null
)

data class PublishedTimeText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class TwoColumnWatchNextResults(

	@field:SerializedName("secondaryResults")
	val secondaryResults: SecondaryResults? = null,

	@field:SerializedName("results")
	val results: Results? = null,

	@field:SerializedName("autoplay")
	val autoplay: Autoplay? = null
)

data class ActionsItem(

	@field:SerializedName("openPopupAction")
	val openPopupAction: OpenPopupAction? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("addToPlaylistCommand")
	val addToPlaylistCommand: AddToPlaylistCommand? = null,

	@field:SerializedName("addedVideoId")
	val addedVideoId: String? = null,

	@field:SerializedName("action")
	val action: String? = null,

	@field:SerializedName("removedVideoId")
	val removedVideoId: String? = null,

	@field:SerializedName("showEngagementPanelEndpoint")
	val showEngagementPanelEndpoint: ShowEngagementPanelEndpoint? = null,

	@field:SerializedName("signalAction")
	val signalAction: SignalAction? = null
)

data class Views(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class CinematicContainerRenderer(

	@field:SerializedName("gradientColorConfig")
	val gradientColorConfig: List<GradientColorConfigItem?>? = null,

	@field:SerializedName("presentationStyle")
	val presentationStyle: String? = null,

	@field:SerializedName("config")
	val config: Config? = null
)

data class GetSurveyCommand(

	@field:SerializedName("endpoint")
	val endpoint: Endpoint? = null,

	@field:SerializedName("action")
	val action: String? = null
)

data class Popup(

	@field:SerializedName("notificationActionRenderer")
	val notificationActionRenderer: NotificationActionRenderer? = null,

	@field:SerializedName("unifiedSharePanelRenderer")
	val unifiedSharePanelRenderer: UnifiedSharePanelRenderer? = null,

	@field:SerializedName("menuPopupRenderer")
	val menuPopupRenderer: MenuPopupRenderer? = null,

	@field:SerializedName("confirmDialogRenderer")
	val confirmDialogRenderer: ConfirmDialogRenderer? = null,

	@field:SerializedName("multiPageMenuRenderer")
	val multiPageMenuRenderer: MultiPageMenuRenderer? = null,

	@field:SerializedName("voiceSearchDialogRenderer")
	val voiceSearchDialogRenderer: VoiceSearchDialogRenderer? = null
)

data class IconImage(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class Label(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class ToggledIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class ThumbnailOverlayHoverTextRenderer(

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("text")
	val text: Text? = null
)

data class CreatorAboutButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ClearButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class LoggingDirectives(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("visibility")
	val visibility: Visibility? = null,

	@field:SerializedName("enableDisplayloggerExperiment")
	val enableDisplayloggerExperiment: Boolean? = null
)

data class ResponseText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class EntityBatchUpdate(

	@field:SerializedName("mutations")
	val mutations: List<MutationsItem?>? = null,

	@field:SerializedName("timestamp")
	val timestamp: Timestamp? = null
)

data class Background(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class StyleRunExtensions(

	@field:SerializedName("styleRunColorMapExtension")
	val styleRunColorMapExtension: StyleRunColorMapExtension? = null
)

data class DesktopTopbarRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("topbarButtons")
	val topbarButtons: List<TopbarButtonsItem?>? = null,

	@field:SerializedName("a11ySkipNavigationButton")
	val a11ySkipNavigationButton: A11ySkipNavigationButton? = null,

	@field:SerializedName("countryCode")
	val countryCode: String? = null,

	@field:SerializedName("hotkeyDialog")
	val hotkeyDialog: HotkeyDialog? = null,

	@field:SerializedName("forwardButton")
	val forwardButton: ForwardButton? = null,

	@field:SerializedName("backButton")
	val backButton: BackButton? = null,

	@field:SerializedName("voiceSearchButton")
	val voiceSearchButton: VoiceSearchButton? = null,

	@field:SerializedName("logo")
	val logo: Logo? = null,

	@field:SerializedName("searchbox")
	val searchbox: Searchbox? = null
)

data class Thumbnail(

	@field:SerializedName("vibrantColorPalette")
	val vibrantColorPalette: VibrantColorPalette? = null,

	@field:SerializedName("sampledThumbnailColor")
	val sampledThumbnailColor: SampledThumbnailColor? = null,

	@field:SerializedName("darkColorPalette")
	val darkColorPalette: DarkColorPalette? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class ChannelEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null
)

data class StyleRunColorMapExtension(

	@field:SerializedName("colorMap")
	val colorMap: List<ColorMapItem?>? = null
)

data class Button(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class EngagementPanelTitleHeaderRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("visibilityButton")
	val visibilityButton: VisibilityButton? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("menu")
	val menu: Menu? = null,

	@field:SerializedName("contextualInfo")
	val contextualInfo: ContextualInfo? = null
)

data class RunsItem(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null,

	@field:SerializedName("loggingDirectives")
	val loggingDirectives: LoggingDirectives? = null
)

data class ThumbnailOverlayToggleButtonRenderer(

	@field:SerializedName("untoggledIcon")
	val untoggledIcon: UntoggledIcon? = null,

	@field:SerializedName("toggledIcon")
	val toggledIcon: ToggledIcon? = null,

	@field:SerializedName("toggledTooltip")
	val toggledTooltip: String? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("toggledAccessibility")
	val toggledAccessibility: ToggledAccessibility? = null,

	@field:SerializedName("untoggledTooltip")
	val untoggledTooltip: String? = null,

	@field:SerializedName("untoggledAccessibility")
	val untoggledAccessibility: UntoggledAccessibility? = null,

	@field:SerializedName("untoggledServiceEndpoint")
	val untoggledServiceEndpoint: UntoggledServiceEndpoint? = null,

	@field:SerializedName("toggledServiceEndpoint")
	val toggledServiceEndpoint: ToggledServiceEndpoint? = null,

	@field:SerializedName("isToggled")
	val isToggled: Boolean? = null
)

data class Endpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null,

	@field:SerializedName("watch")
	val watch: Watch? = null
)

data class ToggledText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class ThumbnailOverlayNowPlayingRenderer(

	@field:SerializedName("text")
	val text: Text? = null
)

data class SubscriptionStateEntity(

	@field:SerializedName("subscribed")
	val subscribed: Boolean? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class MenuNavigationItemRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class ButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class SubscribeAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class ConnectionErrorMicrophoneLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class TooltipText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class UpdateToggleButtonStateCommand(

	@field:SerializedName("toggled")
	val toggled: Boolean? = null,

	@field:SerializedName("buttonId")
	val buttonId: String? = null
)

data class RelativeDateText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class UnsubscribeButtonText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Options(

	@field:SerializedName("persistenceOption")
	val persistenceOption: String? = null
)

data class MultiPageMenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("showLoadingSpinner")
	val showLoadingSpinner: Boolean? = null
)

data class LongBylineText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ToggledAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class CommandsItem(

	@field:SerializedName("openPopupAction")
	val openPopupAction: OpenPopupAction? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("scrollToEngagementPanelCommand")
	val scrollToEngagementPanelCommand: ScrollToEngagementPanelCommand? = null,

	@field:SerializedName("changeEngagementPanelVisibilityAction")
	val changeEngagementPanelVisibilityAction: ChangeEngagementPanelVisibilityAction? = null,

	@field:SerializedName("updateToggleButtonStateCommand")
	val updateToggleButtonStateCommand: UpdateToggleButtonStateCommand? = null
)

data class ButtonRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("isDisabled")
	val isDisabled: Boolean? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null,

	@field:SerializedName("serviceEndpoint")
	val serviceEndpoint: ServiceEndpoint? = null,

	@field:SerializedName("command")
	val command: Command? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null
)

data class ToggleButtonRenderer(

	@field:SerializedName("defaultTooltip")
	val defaultTooltip: String? = null,

	@field:SerializedName("toggledText")
	val toggledText: ToggledText? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("defaultNavigationEndpoint")
	val defaultNavigationEndpoint: DefaultNavigationEndpoint? = null,

	@field:SerializedName("isToggled")
	val isToggled: Boolean? = null,

	@field:SerializedName("toggledTooltip")
	val toggledTooltip: String? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("defaultIcon")
	val defaultIcon: DefaultIcon? = null,

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("style")
	val style: Style? = null,

	@field:SerializedName("defaultText")
	val defaultText: DefaultText? = null,

	@field:SerializedName("isDisabled")
	val isDisabled: Boolean? = null,

	@field:SerializedName("toggleButtonSupportedData")
	val toggleButtonSupportedData: ToggleButtonSupportedData? = null,

	@field:SerializedName("toggledStyle")
	val toggledStyle: ToggledStyle? = null
)

data class CommandExecutorCommand(

	@field:SerializedName("commands")
	val commands: List<CommandsItem?>? = null
)

data class PlayerOverlayVideoDetailsRenderer(

	@field:SerializedName("subtitle")
	val subtitle: Subtitle? = null,

	@field:SerializedName("title")
	val title: Title? = null
)

data class Command(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null,

	@field:SerializedName("getSurveyCommand")
	val getSurveyCommand: GetSurveyCommand? = null,

	@field:SerializedName("continuationCommand")
	val continuationCommand: ContinuationCommand? = null,

	@field:SerializedName("commandExecutorCommand")
	val commandExecutorCommand: CommandExecutorCommand? = null,

	@field:SerializedName("modalEndpoint")
	val modalEndpoint: ModalEndpoint? = null,

	@field:SerializedName("changeEngagementPanelVisibilityAction")
	val changeEngagementPanelVisibilityAction: ChangeEngagementPanelVisibilityAction? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null,

	@field:SerializedName("showReloadUiCommand")
	val showReloadUiCommand: ShowReloadUiCommand? = null
)

data class DefaultIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class StructuredDescriptionContentRenderer(

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class TeaserContent(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class Watch(

	@field:SerializedName("hack")
	val hack: Boolean? = null
)

data class CurrentVideoEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null
)

data class WatchEndpointSupportedOnesieConfig(

	@field:SerializedName("html5PlaybackOnesieConfig")
	val html5PlaybackOnesieConfig: Html5PlaybackOnesieConfig? = null
)

data class BadgesItem(

	@field:SerializedName("metadataBadgeRenderer")
	val metadataBadgeRenderer: MetadataBadgeRenderer? = null
)

data class DateText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class WebResponseContextExtensionData(

	@field:SerializedName("hasDecorated")
	val hasDecorated: Boolean? = null,

	@field:SerializedName("webPrefetchData")
	val webPrefetchData: WebPrefetchData? = null
)

data class EnabledAccessibilityData(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class BrowseEndpoint(

	@field:SerializedName("browseId")
	val browseId: String? = null,

	@field:SerializedName("canonicalBaseUrl")
	val canonicalBaseUrl: String? = null,

	@field:SerializedName("params")
	val params: String? = null
)

data class HotkeyAccessibilityLabel(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class OwnerBadgesItem(

	@field:SerializedName("metadataBadgeRenderer")
	val metadataBadgeRenderer: MetadataBadgeRenderer? = null
)

data class ModalWithTitleAndButtonRenderer(

	@field:SerializedName("button")
	val button: Button? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("content")
	val content: Content? = null
)

data class HotkeyDialogSectionOptionRenderer(

	@field:SerializedName("hotkey")
	val hotkey: String? = null,

	@field:SerializedName("label")
	val label: Label? = null,

	@field:SerializedName("hotkeyAccessibilityLabel")
	val hotkeyAccessibilityLabel: HotkeyAccessibilityLabel? = null
)

data class PlaceholderHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ChannelAvatar(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class CommonConfig(

	@field:SerializedName("url")
	val url: String? = null
)

data class FactoidItem(

	@field:SerializedName("factoidRenderer")
	val factoidRenderer: FactoidRenderer? = null
)

data class SignalAction(

	@field:SerializedName("signal")
	val signal: String? = null
)

data class ModifyChannelNotificationPreferenceEndpoint(

	@field:SerializedName("params")
	val params: String? = null
)

data class ContinuationCommand(

	@field:SerializedName("request")
	val request: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("command")
	val command: Command? = null
)

data class SubscriptionNotificationToggleButtonRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("secondaryIcon")
	val secondaryIcon: SecondaryIcon? = null,

	@field:SerializedName("currentStateId")
	val currentStateId: Int? = null,

	@field:SerializedName("command")
	val command: Command? = null,

	@field:SerializedName("states")
	val states: List<StatesItem?>? = null
)

data class UnsubscribeAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class AttributedDescriptionBodyText(

	@field:SerializedName("styleRuns")
	val styleRuns: List<StyleRunsItem?>? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class SetsItem(

	@field:SerializedName("mode")
	val mode: String? = null,

	@field:SerializedName("autoplayVideo")
	val autoplayVideo: AutoplayVideo? = null
)

data class GetTranscriptEndpoint(

	@field:SerializedName("params")
	val params: String? = null
)

data class AutoplayVideo(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null
)

data class ToggledServiceEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("playlistEditEndpoint")
	val playlistEditEndpoint: PlaylistEditEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class FusionSearchboxRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("clearButton")
	val clearButton: ClearButton? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("placeholderText")
	val placeholderText: PlaceholderText? = null,

	@field:SerializedName("config")
	val config: Config? = null
)

data class MovingThumbnailDetails(

	@field:SerializedName("logAsMovingThumbnail")
	val logAsMovingThumbnail: Boolean? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class Style(

	@field:SerializedName("styleType")
	val styleType: String? = null
)

data class ShareEntityServiceEndpoint(

	@field:SerializedName("serializedShareEntity")
	val serializedShareEntity: String? = null,

	@field:SerializedName("commands")
	val commands: List<CommandsItem?>? = null
)

data class OnCreateListCommand(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("createPlaylistServiceEndpoint")
	val createPlaylistServiceEndpoint: CreatePlaylistServiceEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class ToggleButtonIdData(

	@field:SerializedName("id")
	val id: String? = null
)

data class WebCommandMetadata(

	@field:SerializedName("rootVe")
	val rootVe: Int? = null,

	@field:SerializedName("webPageType")
	val webPageType: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("apiUrl")
	val apiUrl: String? = null,

	@field:SerializedName("sendPost")
	val sendPost: Boolean? = null,

	@field:SerializedName("ignoreNavigation")
	val ignoreNavigation: Boolean? = null
)

data class SearchEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("query")
	val query: String? = null
)

data class SubscriptionButton(

	@field:SerializedName("type")
	val type: String? = null
)

data class PromptMicrophoneLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class WatchEndpoint(

	@field:SerializedName("watchEndpointSupportedOnesieConfig")
	val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("playlistId")
	val playlistId: String? = null,

	@field:SerializedName("continuePlayback")
	val continuePlayback: Boolean? = null,

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("loggingContext")
	val loggingContext: LoggingContext? = null,

	@field:SerializedName("nofollow")
	val nofollow: Boolean? = null,

	@field:SerializedName("playerParams")
	val playerParams: String? = null,

	@field:SerializedName("watchEndpointSupportedPrefetchConfig")
	val watchEndpointSupportedPrefetchConfig: WatchEndpointSupportedPrefetchConfig? = null
)

data class Text(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class MenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("topLevelButtons")
	val topLevelButtons: List<TopLevelButtonsItem?>? = null,

	@field:SerializedName("flexibleItems")
	val flexibleItems: List<FlexibleItemsItem?>? = null
)

data class SampledThumbnailColor(

	@field:SerializedName("red")
	val red: Int? = null,

	@field:SerializedName("green")
	val green: Int? = null,

	@field:SerializedName("blue")
	val blue: Int? = null
)

data class CompactRadioRenderer(

	@field:SerializedName("playlistId")
	val playlistId: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("videoCountText")
	val videoCountText: VideoCountText? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("thumbnailText")
	val thumbnailText: ThumbnailText? = null,

	@field:SerializedName("secondaryNavigationEndpoint")
	val secondaryNavigationEndpoint: SecondaryNavigationEndpoint? = null,

	@field:SerializedName("videoCountShortText")
	val videoCountShortText: VideoCountShortText? = null,

	@field:SerializedName("shareUrl")
	val shareUrl: String? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("thumbnailOverlays")
	val thumbnailOverlays: List<ThumbnailOverlaysItem?>? = null,

	@field:SerializedName("longBylineText")
	val longBylineText: LongBylineText? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class ServiceTrackingParamsItem(

	@field:SerializedName("service")
	val service: String? = null,

	@field:SerializedName("params")
	val params: List<ParamsItem?>? = null
)

data class DismissButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class PlaceholderText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class TeaserAvatar(

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class ServiceEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null,

	@field:SerializedName("shareEntityServiceEndpoint")
	val shareEntityServiceEndpoint: ShareEntityServiceEndpoint? = null,

	@field:SerializedName("modalEndpoint")
	val modalEndpoint: ModalEndpoint? = null,

	@field:SerializedName("modifyChannelNotificationPreferenceEndpoint")
	val modifyChannelNotificationPreferenceEndpoint: ModifyChannelNotificationPreferenceEndpoint? = null,

	@field:SerializedName("unsubscribeEndpoint")
	val unsubscribeEndpoint: UnsubscribeEndpoint? = null,

	@field:SerializedName("continuationCommand")
	val continuationCommand: ContinuationCommand? = null
)

data class SegmentedLikeDislikeButtonRenderer(

	@field:SerializedName("dislikeButton")
	val dislikeButton: DislikeButton? = null,

	@field:SerializedName("likeCount")
	val likeCount: String? = null,

	@field:SerializedName("likeButton")
	val likeButton: LikeButton? = null
)

data class TopLevelButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class VideoCountText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ColorMapItem(

	@field:SerializedName("value")
	val value: Long? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class AccessibilityData(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("label")
	val label: String? = null
)

data class ViewCountText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Value(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class VideoPrimaryInfoRenderer(

	@field:SerializedName("dateText")
	val dateText: DateText? = null,

	@field:SerializedName("relativeDateText")
	val relativeDateText: RelativeDateText? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("videoActions")
	val videoActions: VideoActions? = null,

	@field:SerializedName("viewCount")
	val viewCount: ViewCount? = null,

	@field:SerializedName("superTitleLink")
	val superTitleLink: SuperTitleLink? = null,

	@field:SerializedName("title")
	val title: Title? = null
)

data class MetadataBadgeRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null
)

data class ShowLessCommand(

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("changeEngagementPanelVisibilityAction")
	val changeEngagementPanelVisibilityAction: ChangeEngagementPanelVisibilityAction? = null
)

data class SubscribeButtonRenderer(

	@field:SerializedName("buttonText")
	val buttonText: ButtonText? = null,

	@field:SerializedName("notificationPreferenceButton")
	val notificationPreferenceButton: NotificationPreferenceButton? = null,

	@field:SerializedName("subscribeAccessibility")
	val subscribeAccessibility: SubscribeAccessibility? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("onUnsubscribeEndpoints")
	val onUnsubscribeEndpoints: List<OnUnsubscribeEndpointsItem?>? = null,

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

	@field:SerializedName("signInEndpoint")
	val signInEndpoint: SignInEndpoint? = null,

	@field:SerializedName("subscribed")
	val subscribed: Boolean? = null,

	@field:SerializedName("unsubscribeAccessibility")
	val unsubscribeAccessibility: UnsubscribeAccessibility? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("onSubscribeEndpoints")
	val onSubscribeEndpoints: List<OnSubscribeEndpointsItem?>? = null,

	@field:SerializedName("subscribedEntityKey")
	val subscribedEntityKey: String? = null,

	@field:SerializedName("channelId")
	val channelId: String? = null,

	@field:SerializedName("unsubscribedButtonText")
	val unsubscribedButtonText: UnsubscribedButtonText? = null
)

data class WatchEndpointSupportedPrefetchConfig(

	@field:SerializedName("prefetchHintConfig")
	val prefetchHintConfig: PrefetchHintConfig? = null
)

data class TopbarButtonsItem(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null,

	@field:SerializedName("topbarMenuButtonRenderer")
	val topbarMenuButtonRenderer: TopbarMenuButtonRenderer? = null
)

data class CommentsEntryPointHeaderRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("headerText")
	val headerText: HeaderText? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("onTap")
	val onTap: OnTap? = null,

	@field:SerializedName("contentRenderer")
	val contentRenderer: ContentRenderer? = null,

	@field:SerializedName("commentCount")
	val commentCount: CommentCount? = null
)

data class SubMenuItemsItem(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("serviceEndpoint")
	val serviceEndpoint: ServiceEndpoint? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("selected")
	val selected: Boolean? = null
)

data class CreatePlaylistServiceEndpoint(

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("videoIds")
	val videoIds: List<String?>? = null
)

data class ContinuationItemRenderer(

	@field:SerializedName("button")
	val button: Button? = null,

	@field:SerializedName("continuationEndpoint")
	val continuationEndpoint: ContinuationEndpoint? = null,

	@field:SerializedName("trigger")
	val trigger: String? = null
)

data class CancelButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class MenuFlexibleItemRenderer(

	@field:SerializedName("topLevelButton")
	val topLevelButton: TopLevelButton? = null,

	@field:SerializedName("menuItem")
	val menuItem: MenuItem? = null
)

data class OnTap(

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("commandExecutorCommand")
	val commandExecutorCommand: CommandExecutorCommand? = null
)

data class EngagementPanelsItem(

	@field:SerializedName("engagementPanelSectionListRenderer")
	val engagementPanelSectionListRenderer: EngagementPanelSectionListRenderer? = null
)

data class WebPrefetchData(

	@field:SerializedName("navigationEndpoints")
	val navigationEndpoints: List<NavigationEndpointsItem?>? = null
)

data class UntoggledIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class Channel(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class FlexibleItemsItem(

	@field:SerializedName("menuFlexibleItemRenderer")
	val menuFlexibleItemRenderer: MenuFlexibleItemRenderer? = null
)

data class LengthText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class Title(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class MenuRequest(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null
)

data class SectionsItem(

	@field:SerializedName("hotkeyDialogSectionRenderer")
	val hotkeyDialogSectionRenderer: HotkeyDialogSectionRenderer? = null
)

data class ConnectionErrorHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class DefaultText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class VisibilityButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ChannelNavigationEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null
)

data class ExpandableVideoDescriptionBodyRenderer(

	@field:SerializedName("showMoreText")
	val showMoreText: ShowMoreText? = null,

	@field:SerializedName("attributedDescriptionBodyText")
	val attributedDescriptionBodyText: AttributedDescriptionBodyText? = null,

	@field:SerializedName("showLessText")
	val showLessText: ShowLessText? = null
)

data class MicrophoneOffPromptHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class DisabledHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)
